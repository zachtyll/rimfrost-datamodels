package se.fk.mimer.codec.v2.jsonld;

import com.fasterxml.jackson.databind.JsonNode;

public final class JsonLdExtractor
{
    public static JsonNode dataNode(JsonNode root) {
        return root == null ? null : root.get( JsonLdKeys.DATA );
    }

    public static JsonNode rawDataNode(JsonNode root) {
        return root == null ? null : root.get( JsonLdKeys.RAW_DATA );
    }

    public static String typeId( JsonNode node) {
        if (node == null) return null;

        JsonNode typeNode = node.get( JsonLdKeys.TYPE );
        if (typeNode == null || typeNode.isNull()) return null;

        if (!typeNode.isTextual()) return null;

        String typeId = typeNode.asText();
        return typeId.isBlank() ? null : typeId;
    }

    private JsonLdExtractor() {}
}
