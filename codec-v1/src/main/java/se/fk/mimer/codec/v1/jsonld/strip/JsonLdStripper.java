package se.fk.mimer.codec.v1.jsonld.strip;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;

import java.util.Set;

/**
 * Strippar JSON-LD-nyckelord och prefix från fältnamn.
 *
 */
public final class JsonLdStripper
{
    private static final Set<String> STRIP_KEYS = Set.of(
            JsonLdKeys.ID,
            JsonLdKeys.TYPE_ID,
            JsonLdKeys.CONTEXT
    );

    private final ObjectMapper mapper;

    public JsonLdStripper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Strippar JSON-LD-nyckelord och prefix från fältnamn i given nod.
     *
     * @param graphNode json-ld noden
     * @return strippat träd
     */
    public JsonNode stripForDecode(ObjectNode graphNode) {
        return stripNode(graphNode);
    }

    private ObjectNode stripNode(ObjectNode source) {
        ObjectNode out = mapper.createObjectNode();

        source.properties().forEach( entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();

            if (STRIP_KEYS.contains(key)) return;

            if (JsonLdKeys.TYPE.equals(key)) {
                out.set(key, value);
                return;
            }

            String stripped = stripPrefix(key);

            if (value.isObject()) {
                out.set(stripped, stripNode((ObjectNode) value));
            }
            else if (value.isArray()) {
                out.set(stripped, stripArray((ArrayNode) value));
            }
            else {
                out.set(stripped, value);
            }
        });

        return out;
    }

    private ArrayNode stripArray(ArrayNode array) {
        ArrayNode out = mapper.createArrayNode();
        array.forEach(item -> {
            if (item.isObject()) out.add(stripNode((ObjectNode) item));
            else out.add(item);
        });
        return out;
    }

    private String stripPrefix(String key) {
        int colon = key.indexOf(":");
        return colon >= 0 ? key.substring(colon+1) : key;
    }

}
