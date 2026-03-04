package se.fk.mimer.codec.v1.jsonld;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;


public final class JsonLdStripper
{
    private JsonLdStripper() {}

    /**
     * Recursively removes JSON-LD keys (@type and @Context) from an existing tree.
     * Mutates the provided node (and its children) in-place.
     *
     * Safe to call on any JsonNode (object/array).
     */
    public static void stripInPlace( JsonNode node) {
        if (node == null) return;

        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            // remove JSON-LD metadata keys if present
            objectNode.remove( JsonLdKeys.TYPE );
            objectNode.remove( JsonLdKeys.CONTEXT );

            // recurse into fields
            for ( Map.Entry<String, JsonNode> entry : objectNode.properties()) {
                stripInPlace( entry.getValue() );
            }
            return;
        }

        if( node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            for (JsonNode child : arrayNode) {
                stripInPlace( child );
            }
        }
    }
}
