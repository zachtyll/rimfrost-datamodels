package se.fk.mimer.codec.v2.jsonld;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import se.fk.mimer.codec.v2.exceptions.DecodeException;

import java.util.Objects;

/**
 * Implementation av {@link PayloadInspector}.
 */
@RequiredArgsConstructor
public final class DataPayloadInspector implements PayloadInspector
{
    private final ObjectMapper mapper;

    @Override
    public byte[] extractRawDataJsonBytes( byte[] payloadBytes )
    {
        JsonNode root = parseRoot(payloadBytes);
        JsonNode raw = requireObject(root, JsonLdKeys.RAW_DATA);
        return write(raw);
    }

    @Override
    public byte[] extractDataJsonBytes( byte[] payloadBytes )
    {
        JsonNode root = parseRoot( payloadBytes );
        JsonNode data = requireObject( root, JsonLdKeys.DATA );
        return write(data);
    }

    private JsonNode parseRoot(byte[] payloadBytes) {
        Objects.requireNonNull( payloadBytes, "payloadBytes must not be null" );

        try {
            JsonNode root = mapper.readTree( payloadBytes );

            if (root == null || !root.isObject()) {
                throw new DecodeException( "Payload root must be a JSON object" );
            }
            return root;
        } catch( DecodeException e ) {
            throw e;
        } catch (Exception e) {
            throw new DecodeException( "Failed to parse payloadBytes", e );
        }
    }

    private JsonNode requireObject(JsonNode parent, String fieldName) {
        JsonNode node = parent.get( fieldName );

        if (node == null || node.isNull()) {
            throw new DecodeException( "Missing '" + fieldName + "'" );
        }
        if (!node.isObject()) {
            throw new DecodeException( "'" + fieldName + "' must be a JSON object");
        }
        return node;
    }

    private byte[] write(JsonNode node) {
        try{
            return mapper.writeValueAsBytes( node );
        } catch (Exception e) {
            throw new DecodeException( "Failed to serialize extracted node", e );
        }
    }
}
