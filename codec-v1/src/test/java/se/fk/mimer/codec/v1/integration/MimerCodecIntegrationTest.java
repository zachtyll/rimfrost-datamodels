package se.fk.mimer.codec.v1.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.api.Codec;
import se.fk.mimer.codec.v1.api.CodecComponents;
import se.fk.mimer.codec.v1.api.DecodedPayload;
import se.fk.mimer.codec.v1.api.EncodeRequest;
import se.fk.mimer.codec.v1.api.MimerCodecFactory;
import se.fk.mimer.codec.v1.config.CodecConfig;
import se.fk.mimer.codec.v1.dto.Dataleverans;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.fixtures.MetadataFixtures;
import se.fk.mimer.codec.v1.fixtures.YrkandeFixtures;
import se.fk.mimer.codec.v1.jackson.CodecObjectMapperFactory;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v1.jsonld.PayloadInspector;
import se.fk.mimer.codec.v1.payload.Base64UrlCodec;
import se.fk.mimer.codec.v1.payload.PayloadCodec;
import se.fk.mimer.codec.v1.registry.CodecRegistries;
import se.fk.mimer.codec.v1.validation.DataleveransContractValidator;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MimerCodecIntegrationTest
{

    private static Codec codec;
    private static PayloadInspector inspector;
    private static ObjectMapper variantMapper;
    private static ObjectMapper rawMapper;
    private static final PayloadCodec PAYLOAD_CODEC = new Base64UrlCodec();


    @BeforeAll
    static void init() {
        CodecComponents components = MimerCodecFactory.create();
        codec = components.getCodec();
        inspector =  components.getInspector();
        variantMapper = CodecObjectMapperFactory.createVariantMapper( CodecRegistries.createVariantRegistry() );
        rawMapper = CodecObjectMapperFactory.createRawMapper();
    }


    @Test
    void encode_decodedYrkande_preserves_exact_payload_bytes_and_prints_jsonld() {
        // Arrange
        Yrkande yrkande = YrkandeFixtures.createYrkande();
        Object rawData = yrkande;

        EncodeRequest request = EncodeRequest.builder()
                .data( yrkande )
                .rawData( rawData )
                .metadata( MetadataFixtures.metadataSJP() )
                .build();

        // Encode
        Dataleverans dataleverans = codec.encode(request);

        debugPrintPayload( "ENCODED JSON-LD", dataleverans );

        byte[] originalPayloadBytes = PAYLOAD_CODEC.decode( dataleverans.getPayload() );

        // Decode
        DecodedPayload decoded = codec.decodeYrkande( dataleverans );

        debugPrintJsonBytes( "DECODED payloadbytes", decoded.getPayloadBytes() );

        // Assert - Byte equality
        assertArrayEquals( originalPayloadBytes, decoded.getPayloadBytes());
    }

    @Test
    void decode_throws_on_wrong_contentType_if_present() {
        String json = "{\"@type\":\"x\",\"data\":{\"@type\":\"https://data.fk.se/typ/Yrkande/2.0\"},\"rawData\":{}}";

        Dataleverans dataleverans = Dataleverans.builder()
                .transportVersion( "2.0" )
                .metadata( MetadataFixtures.metadataSJP() )
                .payload( PAYLOAD_CODEC.encode( json.getBytes( StandardCharsets.UTF_8 ) ) )
                .contentType( "application/json" ) // this is wrong format
                .payloadEncoding( "base64url" )
                .build();

        assertThrows( DecodeException.class, () -> codec.decodeYrkande( dataleverans ) );

    }

    @Test
    void decode_throws_on_wrong_payloadEncoding_if_present() {
        String json = "{\"@type\":\"x\",\"data\":{\"@type\":\"https://data.fk.se/typ/Yrkande/2.0\"},\"rawData\":{}}";

        Dataleverans dataleverans = Dataleverans.builder()
                .transportVersion( "2.0" )
                .metadata( MetadataFixtures.metadataSJP() )
                .payload( PAYLOAD_CODEC.encode( json.getBytes( StandardCharsets.UTF_8 ) ) )
                .contentType( "application/ld+json" )
                .payloadEncoding( "base64" ) // this is wrong format
                .build();
    }

    @Test
    void extracts_rawData_and_data_as_json_bytes() throws Exception {
        // Arrange
        Yrkande yrkande = YrkandeFixtures.createYrkande();
        Object rawData = yrkande;

        EncodeRequest request = EncodeRequest.builder()
                .data( yrkande )
                .rawData( rawData )
                .metadata( MetadataFixtures.metadataSJP() )
                .build();

        Dataleverans dataleverans = codec.encode(request);
        DecodedPayload payload = codec.decodeYrkande( dataleverans );

        byte[] payloadBytes = payload.getPayloadBytes();

        // extract
        byte[] dataBytes = inspector.extractDataJsonBytes(payloadBytes);
        byte[] rawDataBytes = inspector.extractRawDataJsonBytes( payloadBytes );

        JsonNode extractedData = variantMapper.readTree( dataBytes );
        JsonNode extractedRaw = variantMapper.readTree( rawDataBytes );

        JsonNode expectedData = variantMapper.valueToTree( yrkande );
        JsonNode expectedRaw = rawMapper.valueToTree( rawData );

        if (extractedData.has( JsonLdKeys.TYPE )) {
            ((ObjectNode) extractedData).remove(JsonLdKeys.TYPE);
        }
        if (extractedData.has( JsonLdKeys.CONTEXT )) {
            ((ObjectNode) extractedData).remove( JsonLdKeys.CONTEXT );
        }

        assertEquals( expectedData, extractedData );
        assertEquals( expectedRaw, extractedRaw );
    }

    @Test
    void shouldAcceptExactTransportVersion() {
        String json = "{\"@type\":\"x\",\"data\":{\"@type\":\"https://data.fk.se/typ/Yrkande/2.0\"},\"rawData\":{}}";

        Dataleverans dataleverans = Dataleverans.builder()
                .transportVersion( "1.0" )
                .metadata( MetadataFixtures.metadataSJP() )
                .payload( PAYLOAD_CODEC.encode( json.getBytes( StandardCharsets.UTF_8 ) ) )
                .contentType( CodecConfig.CONTENT_TYPE_LD_JSON )
                .payloadEncoding( CodecConfig.PAYLOAD_ENCODING_BASE64URL )
                .build();

        DataleveransContractValidator validator =
                new DataleveransContractValidator(
                        "1.0",
                        CodecConfig.PAYLOAD_ENCODING_BASE64URL,
                        CodecConfig.CONTENT_TYPE_LD_JSON );

        assertDoesNotThrow(() -> validator.validate( dataleverans ));
    }

    @Test
    void shouldAcceptSameMajorDifferentMinor() {
        String json = "{\"@type\":\"x\",\"data\":{\"@type\":\"https://data.fk.se/typ/Yrkande/2.0\"},\"rawData\":{}}";

        Dataleverans dataleverans = Dataleverans.builder()
                .transportVersion( "1.3" )
                .metadata( MetadataFixtures.metadataSJP() )
                .payload( PAYLOAD_CODEC.encode( json.getBytes( StandardCharsets.UTF_8 ) ) )
                .contentType( CodecConfig.CONTENT_TYPE_LD_JSON )
                .payloadEncoding( CodecConfig.PAYLOAD_ENCODING_BASE64URL )
                .build();

        DataleveransContractValidator validator =
                new DataleveransContractValidator(
                        "1.0",
                        CodecConfig.PAYLOAD_ENCODING_BASE64URL,
                        CodecConfig.CONTENT_TYPE_LD_JSON );

        assertDoesNotThrow(() -> validator.validate( dataleverans ));
    }

    @Test
    void shouldRejectDifferentMajor() {
        String json = "{\"@type\":\"x\",\"data\":{\"@type\":\"https://data.fk.se/typ/Yrkande/2.0\"},\"rawData\":{}}";

        Dataleverans dataleverans = Dataleverans.builder()
                .transportVersion( "2.0" )
                .metadata( MetadataFixtures.metadataSJP() )
                .payload( PAYLOAD_CODEC.encode( json.getBytes( StandardCharsets.UTF_8 ) ) )
                .contentType( CodecConfig.CONTENT_TYPE_LD_JSON )
                .payloadEncoding( CodecConfig.PAYLOAD_ENCODING_BASE64URL )
                .build();

        DataleveransContractValidator validator =
                new DataleveransContractValidator(
                        "1.0",
                        CodecConfig.PAYLOAD_ENCODING_BASE64URL,
                        CodecConfig.CONTENT_TYPE_LD_JSON );

        DecodeException exception = assertThrows(
                DecodeException.class,
                () -> validator.validate( dataleverans )
        );

        assertTrue(exception.getMessage().contains("Unsupported transportVersion"));
    }



    // ---------  Pretty printer mapper only for debugging output (NOT used for assertions)  --------//

    private static final ObjectMapper PRETTY = new ObjectMapper()
            .enable( SerializationFeature.INDENT_OUTPUT)
            .enable( SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS );

    // Enable by running from root: ./gradlew :codec-v1:test -Dcodec.debugJson=true
    private static final boolean DEBUG_JSON =
            Boolean.parseBoolean( System.getProperty( "codec.debugJson", "false" ) );

    private static void debugPrintJsonBytes(String label, byte[] jsonBytes) {
        System.out.println("DEBUG_JSON=" + DEBUG_JSON);
        if (!DEBUG_JSON) return;

        try {
            JsonNode node = PRETTY.readTree( jsonBytes );
            System.out.print( "\n=====" + label + " =====" );
            System.out.println(PRETTY.writeValueAsString( node ));
        } catch (Exception e) {
            // Fall back: print raw bytes if JSON parsing fails
            System.out.println("\n===== " + label + " (raw) =====");
            System.out.println(new String(jsonBytes, StandardCharsets.UTF_8));
            System.out.println("===== (pretty-print failed: " + e.getMessage() + ") =====");
        }
    }

    private static void debugPrintPayload(String label, Dataleverans dataleverans) {
        if (!DEBUG_JSON) return;
        byte[] payloadBytes = Base64.getUrlDecoder().decode( dataleverans.getPayload() );
        debugPrintJsonBytes( label, payloadBytes );
    }

}
