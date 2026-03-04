package se.fk.mimer.codec.v2.payload;

import com.fasterxml.jackson.databind.JsonNode;
import se.fk.mimer.codec.v2.exceptions.DecodeException;
import se.fk.mimer.codec.v2.jsonld.JsonLdExtractor;
import se.fk.mimer.codec.v2.jsonld.JsonLdKeys;

public final class PayloadFormatValidator
{
    private PayloadFormatValidator() {}

    public static void validateRoot( JsonNode root) {
        if (root == null || !root.isObject()) {
            throw new DecodeException( "Payload root must be a JSON object" );
        }

        requireTextField(root, JsonLdKeys.TYPE, "payload root" );

        JsonNode data = JsonLdExtractor.dataNode( root );
        if (data == null || data.isNull()) {
            throw new DecodeException( "Missing '" + JsonLdKeys.DATA + "'" );
        }
        if (!data.isObject()) {
            throw new DecodeException( "'" + JsonLdKeys.DATA + "' must be a JSON object" );
        }

        JsonNode raw = JsonLdExtractor.rawDataNode( root );
        if (raw == null || raw.isNull()) {
            throw new DecodeException( "Missing ' " + JsonLdKeys.RAW_DATA + "'");
        }
        if (!raw.isObject()) {
            throw new DecodeException( "'" + JsonLdKeys.RAW_DATA + "' must be a JSON object");
        }

        String dataType = JsonLdExtractor.typeId( data );
        if (dataType == null || dataType.isBlank()) {
            throw new DecodeException( "Missing '" + JsonLdKeys.TYPE + "' in '" + JsonLdKeys.DATA + "'");
        }
    }

    private static void requireTextField(JsonNode obj, String field, String where) {
        JsonNode node = obj.get(field);
        if (node == null || node.isNull() || !node.isTextual() || node.asText().isBlank()) {
            throw new DecodeException( "Missing '" + field + "' in " + where);
        }
    }
}
