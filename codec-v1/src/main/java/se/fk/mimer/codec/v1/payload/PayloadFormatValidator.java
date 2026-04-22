package se.fk.mimer.codec.v1.payload;

import com.fasterxml.jackson.databind.JsonNode;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.jsonld.extract.JsonLdExtractor;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;

public final class PayloadFormatValidator
{
    private PayloadFormatValidator() {}

    public static void validateRoot( JsonNode root) {
        if (root == null || !root.isObject()) {
            throw new DecodeException( "Payload root must be a JSON object" );
        }

        JsonNode baseData = JsonLdExtractor.baseDataNode( root );
        if (baseData == null || baseData.isNull()) {
            throw new DecodeException( "Missing '" + JsonLdKeys.GRAPH + "'" );
        }
        if (!baseData.isObject()) {
            throw new DecodeException( "'" + JsonLdKeys.GRAPH + "' must be a JSON object" );
        }

        JsonNode raw = JsonLdExtractor.rawDataNode( root );
        if (raw == null || raw.isNull()) {
            throw new DecodeException( "Missing ' " + JsonLdKeys.RAW_DATA + "'");
        }
        if (!raw.isObject()) {
            throw new DecodeException( "'" + JsonLdKeys.RAW_DATA + "' must be a JSON object");
        }

        String dataType = JsonLdExtractor.typeId( baseData );
        if (dataType == null || dataType.isBlank()) {
            throw new DecodeException( "Missing '" + JsonLdKeys.TYPE_ID + "' in '" + JsonLdKeys.GRAPH + "'");
        }
    }
}
