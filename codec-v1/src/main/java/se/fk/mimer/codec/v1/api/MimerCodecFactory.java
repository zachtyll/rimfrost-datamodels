package se.fk.mimer.codec.v1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import se.fk.mimer.codec.v1.config.CodecConfig;
import se.fk.mimer.codec.v1.jackson.CodecObjectMappers;
import se.fk.mimer.codec.v1.jsonld.builder.ContextBuilder;
import se.fk.mimer.codec.v1.jsonld.builder.JsonLdGraphBuilder;
import se.fk.mimer.codec.v1.jsonld.builder.JsonLdPayloadBuilder;
import se.fk.mimer.codec.v1.jsonld.builder.NodeTraverser;
import se.fk.mimer.codec.v1.jsonld.context.ContextProvider;
import se.fk.mimer.codec.v1.jsonld.context.StaticContextProvider;
import se.fk.mimer.codec.v1.jsonld.extract.DataPayloadInspector;
import se.fk.mimer.codec.v1.jsonld.extract.PayloadInspector;
import se.fk.mimer.codec.v1.jsonld.field.FieldNameResolver;
import se.fk.mimer.codec.v1.jsonld.field.FieldToClassMapBuilder;
import se.fk.mimer.codec.v1.jsonld.strip.JsonLdStripper;
import se.fk.mimer.codec.v1.payload.Base64UrlCodec;
import se.fk.mimer.codec.v1.payload.PayloadCodec;
import se.fk.mimer.codec.v1.payload.PayloadParser;
import se.fk.mimer.codec.v1.registry.*;
import se.fk.mimer.codec.v1.validation.ContractValidator;
import se.fk.mimer.codec.v1.validation.DataleveransContractValidator;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.util.Map;

/**
 * Factory för att skapa en komplett codec v1-konfiguration.
 *
 * <p>
 * Bygger och konfiguerar nödvändiga komponenter såsom:
 * registries, {@link ObjectMapper}, payload-kodning,
 * JSON-parsning och validering.
 */
public final class MimerCodecFactory
{
    private MimerCodecFactory() {}

    public static CodecComponents create()
    {
        VariantRegistry variantRegistry = CodecRegistries.createVariantRegistry();
        TypeRegistry typeRegistry = CodecRegistries.createTypeRegistry();

        RegistryValidator.ValidateCoreOrThrow( typeRegistry, Yrkande.class, Handlaggning.class );

        CodecObjectMappers mappers = new CodecObjectMappers(variantRegistry, typeRegistry);
        PayloadParser parser = new PayloadParser(mappers.variant());
        PayloadCodec payloadCodec = new Base64UrlCodec();

        ContextProvider contextProvider = new StaticContextProvider();
        ContextBuilder contextBuilder = new ContextBuilder(contextProvider, mappers.raw());
        FieldNameResolver fieldResolver = new FieldNameResolver();
        Map<String, Class<?>> fieldToClass = FieldToClassMapBuilder.build(typeRegistry.getRegisteredClasses());

        NodeTraverser traverser = new NodeTraverser(
                fieldResolver,
                typeRegistry,
                fieldToClass,
                mappers.raw()
        );
        JsonLdPayloadBuilder jsonLdPayloadBuilder = new JsonLdGraphBuilder(contextBuilder, traverser, mappers.raw());

        JsonLdStripper stripper = new JsonLdStripper(mappers.raw());

        Validator beanValidator = Validation.buildDefaultValidatorFactory().getValidator();
        ContractValidator contractValidator = new ContractValidator( beanValidator );

        DataleveransContractValidator dataleveransContractValidator =
                new DataleveransContractValidator( CodecConfig.TRANSPORT_VERSION, CodecConfig.PAYLOAD_ENCODING_BASE64URL, CodecConfig.CONTENT_TYPE_LD_JSON );

        Codec codec = new MimerCodec(
                mappers.variant(),
                mappers.raw(),
                parser,
                payloadCodec,
                jsonLdPayloadBuilder,
                stripper,
                typeRegistry,
                contractValidator,
                dataleveransContractValidator,
                CodecConfig.TRANSPORT_VERSION,
                CodecConfig.CONTENT_TYPE_LD_JSON,
                CodecConfig.PAYLOAD_ENCODING_BASE64URL
        );

        PayloadInspector inspector = new DataPayloadInspector( mappers.raw() );

        return new CodecComponents( codec, inspector );
    }
}
