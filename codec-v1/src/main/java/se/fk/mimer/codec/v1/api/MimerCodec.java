package se.fk.mimer.codec.v1.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import se.fk.mimer.codec.v1.dto.Dataleverans;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.exceptions.EncodeException;
import se.fk.mimer.codec.v1.jsonld.builder.JsonLdPayloadBuilder;
import se.fk.mimer.codec.v1.jsonld.extract.JsonLdExtractor;
import se.fk.mimer.codec.v1.jsonld.extract.PayloadInspector;
import se.fk.mimer.codec.v1.jsonld.strip.JsonLdStripper;
import se.fk.mimer.codec.v1.payload.PayloadCodec;
import se.fk.mimer.codec.v1.payload.PayloadFormatValidator;
import se.fk.mimer.codec.v1.payload.PayloadParser;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.codec.v1.validation.ContractValidator;
import se.fk.mimer.codec.v1.validation.DataleveransContractValidator;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.util.Objects;

/**
 * Implementation av {@link Codec}.
 *
 * <p>
 * Encode bygger JSON-LD-envelope ({@code @context} + {@code @graph}, + {@code rawData}),
 * serialiserar till UTF-8 bytes, base64Url-kodar och kaplsar i {@link Dataleverans}.
 *
 * <p>
 * Decode avkodar payload till byte-exakta JSON-LD-bytes, validerar envelope-strukture och gör strikt
 * typkontroll via {@code @type}. Returnerar payload bytes som source of truth.
 */
@RequiredArgsConstructor
public class MimerCodec implements Codec
{
    private final ObjectMapper variantMapper;
    private final ObjectMapper rawMapper;
    private final PayloadParser parser;
    private final PayloadCodec payloadCodec;
    private final JsonLdPayloadBuilder jsonLdPayloadBuilder;
    private final JsonLdStripper stripper;
    private final TypeRegistry typeRegistry;
    private static final PayloadInspector inspector = MimerCodecFactory.create().getInspector();

    // Validators
    private final ContractValidator contractValidator;
    private final DataleveransContractValidator dataleveransContractValidator;

    // DTO wrapper contract version
    private final String transportVersion;
    private final String contentType;
    private final String payloadEncoding;

    @Override
    public Dataleverans encode( EncodeRequest request )
    {
        try {
            Objects.requireNonNull(request, "request must not be null");
            contractValidator.validate(request);
            contractValidator.validate(request.getData());

            requireNonBlank(transportVersion, "transportVersion");
            requireNonBlank(contentType, "contentType");
            requireNonBlank(payloadEncoding, "payloadEncoding");

            ObjectNode dataNode = ensureObjectNode(variantMapper.valueToTree( request.getData() ), "data");
            ObjectNode rawNode = ensureObjectNode(rawMapper.valueToTree( request.getRawData() ), "rawData");

            String dataTypeIri = typeRegistry.typeIdForClass( request.getData().getClass() );
            String modelVersion = typeRegistry.modelVersionForTypeId( dataTypeIri );
            if (modelVersion == null || modelVersion.isBlank()) {
                throw new EncodeException( "could not derive modelVersion from data @type: " + dataTypeIri);
            }

            ObjectNode root = jsonLdPayloadBuilder.build(
                    dataNode,
                    rawNode,
                    request.getData().getClass(),
                    request.getMetadata().getProducentId(),
                    modelVersion
            );

            byte[] payloadBytes = variantMapper.writeValueAsBytes( root );
            String payload = payloadCodec.encode( payloadBytes );

            Dataleverans dataleverans = Dataleverans.builder()
                    .transportVersion( transportVersion )
                    .metadata( request.getMetadata() )
                    .payload( payload )
                    .contentType( contentType )
                    .payloadEncoding( payloadEncoding )
                    .build();

            contractValidator.validate( dataleverans );
            return dataleverans;

        } catch (EncodeException e) {
            throw e;
        } catch (Exception e) {
            throw new EncodeException( "Encode failed", e );
        }
    }

    @Override
    public DecodedPayload decodeYrkande( Dataleverans dataleverans )
    {
        return decode(dataleverans, Yrkande.class);
    }

    @Override
    public DecodedPayload decodeHandlaggning( Dataleverans dataleverans )
    {
        return decode(dataleverans, Handlaggning.class );
    }

    @Override
    public Object toPojo( byte[] payloadBytes, Class<?> expectedBaseClass )
    {
        try{
            JsonNode root = parser.parse( payloadBytes );

            PayloadFormatValidator.validateRoot( root );

            JsonNode baseDataNode = JsonLdExtractor.baseDataNode( root );
            String typeIri = JsonLdExtractor.typeId(baseDataNode);

            Class<?> actualBaseClass = typeRegistry.classForTypeId( typeIri );
            if (actualBaseClass == null) {
                throw new DecodeException( "Unknown baseData type: " + typeIri );
            }

            if (!expectedBaseClass.equals( actualBaseClass )) {
                throw new DecodeException( "Wrong payload type for endpoint. Expected: "
                        + expectedBaseClass.getSimpleName()
                        + " but payload @type resolved to: "
                        + actualBaseClass.getSimpleName()
                        + " (" + typeIri + ")");
            }

            ObjectNode dataCopy = baseDataNode.deepCopy();
            JsonNode stripped = stripper.stripForDecode(dataCopy);

            Object dataPojo = variantMapper.treeToValue( stripped, expectedBaseClass );
            contractValidator.validate( dataPojo );

            return dataPojo;
        } catch( DecodeException e )
        {
            throw new DecodeException( e.getMessage() );
        }
        catch( Exception e )
        {
            throw new DecodeException( "Decode failed" );
        }
    }

    private DecodedPayload decode( Dataleverans dataleverans, Class<?> expectedBaseClass) {
        try {
            Objects.requireNonNull( dataleverans, "dataleverans must not be null" );
            Objects.requireNonNull( expectedBaseClass, "expectedBaseClass must not be null" );

            dataleveransContractValidator.validate( dataleverans );

            byte[] payloadBytes = payloadCodec.decode( dataleverans.getPayload() );
            toPojo( payloadBytes, expectedBaseClass );

            return DecodedPayload.builder()
                    .payloadBytes( payloadBytes )
                    .build();

        } catch ( DecodeException e ) {
            throw e;
        } catch (Exception e) {
            throw new DecodeException( "Decode failed", e );
        }
    }

    @Override
    public byte[] extractRawDataJsonBytes( byte[] payloadBytes )
    {
        return inspector.extractRawDataJsonBytes( payloadBytes );
    }

    private static void requireNonBlank(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be blank");
        }
    }

    private static ObjectNode ensureObjectNode( JsonNode node, String label) {
        if (!(node instanceof ObjectNode objectNode)) {
            throw new EncodeException( label + " must serialize to a JSON object node" );
        }
        return objectNode;
    }
}
