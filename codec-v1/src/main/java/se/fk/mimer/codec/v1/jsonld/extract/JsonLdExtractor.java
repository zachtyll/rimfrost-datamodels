package se.fk.mimer.codec.v1.jsonld.extract;

import com.fasterxml.jackson.databind.JsonNode;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;

public final class JsonLdExtractor
{
    public static JsonNode baseDataNode(JsonNode root) {
        if (root == null) {
            return null;
        }
        JsonNode graph = root.get(JsonLdKeys.GRAPH);
        if (graph == null || !graph.isArray() || graph.isEmpty()) {
            throw new DecodeException("Missing or empty '@graph' in payload");
        }
        JsonNode node = graph.get(0);
        if (!node.isObject()) {
            throw new DecodeException("'@graph[0]' is not a JSON object");
        }
        return node;
    }

    public static JsonNode rawDataNode(JsonNode root) {
        if (root == null) {
            return null;
        }
        JsonNode rawData = root.get(JsonLdKeys.RAW_DATA);
        if (rawData == null || !rawData.isObject()) {
            throw new DecodeException("Missing or invalid 'rawData' in payload");
        }
        return rawData;
    }

    public static String typeId( JsonNode node) {
        if (node == null) return null;

        JsonNode typeNode = node.get( JsonLdKeys.TYPE_ID );
        if (typeNode == null || typeNode.isNull()) return null;

        if (!typeNode.isTextual()) return null;

        String typeId = typeNode.asText();
        return typeId.isBlank() ? null : typeId;
    }

    private JsonLdExtractor() {}
}
