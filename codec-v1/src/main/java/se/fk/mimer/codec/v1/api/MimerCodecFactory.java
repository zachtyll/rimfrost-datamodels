package se.fk.mimer.codec.v1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import se.fk.mimer.codec.v1.config.CodecConfig;
import se.fk.mimer.codec.v1.jackson.CodecObjectMapperFactory;
import se.fk.mimer.codec.v1.jackson.CodecObjectMappers;
import se.fk.mimer.codec.v1.jsonld.DataPayloadInspector;
import se.fk.mimer.codec.v1.jsonld.JsonLdEnvelopeBuilder;
import se.fk.mimer.codec.v1.jsonld.PayloadInspector;
import se.fk.mimer.codec.v1.payload.Base64UrlCodec;
import se.fk.mimer.codec.v1.payload.PayloadCodec;
import se.fk.mimer.codec.v1.payload.PayloadParser;
import se.fk.mimer.codec.v1.registry.CodecRegistries;
import se.fk.mimer.codec.v1.registry.DummyEmptyContextProvider;
import se.fk.mimer.codec.v1.registry.RegistryValidator;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.codec.v1.registry.VariantRegistry;
import se.fk.mimer.codec.v1.validation.ContractValidator;
import se.fk.mimer.codec.v1.validation.DataleveransContractValidator;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

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

        CodecObjectMappers mappers = new CodecObjectMappers(variantRegistry);
        PayloadParser parser = new PayloadParser(mappers.variant());
        PayloadCodec payloadCodec = new Base64UrlCodec();

        // A dummy context provider for now
        DummyEmptyContextProvider contextProvider = new DummyEmptyContextProvider();

        JsonLdEnvelopeBuilder envelopeBuilder = new JsonLdEnvelopeBuilder( typeRegistry, contextProvider );

        Validator beanValidator = Validation.buildDefaultValidatorFactory().getValidator();
        ContractValidator contractValidator = new ContractValidator( beanValidator );

        DataleveransContractValidator dataleveransContractValidator =
                new DataleveransContractValidator( CodecConfig.TRANSPORT_VERSION, CodecConfig.PAYLOAD_ENCODING_BASE64URL, CodecConfig.CONTENT_TYPE_LD_JSON );

        Codec codec = new MimerCodec(
                mappers.variant(),
                mappers.raw(),
                parser,
                payloadCodec,
                envelopeBuilder,
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
