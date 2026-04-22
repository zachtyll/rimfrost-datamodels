package se.fk.mimer.codec.v1.jsonld.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import se.fk.mimer.codec.v1.exceptions.JsonLdMappingException;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v1.jsonld.context.JsonLdPrefix;
import se.fk.mimer.codec.v1.jsonld.field.FieldNameResolver;
import se.fk.mimer.codec.v1.registry.TypeRegistry;

import java.util.Map;

/**
 * Traverserar ett {@link JsonNode}-träd rekursivt och producerar
 * ett JSON-LD-kompatibelt träd.
 *
 *<p> En nod behandlas som graf-nod om:
 * <ol>
 *     <li>JSON-objektet har ett {@code id}-fält</li>
 *     <li>Klassen är registrerad i {@link TypeRegistry}</li>
 * </ol>
 */
public class NodeTraverser {

    private final FieldNameResolver fieldResolver;
    private final TypeRegistry typeRegistry;
    private final Map<String, Class<?>> fieldToClass;
    private final ObjectMapper mapper;

    public NodeTraverser(
            FieldNameResolver fieldResolver,
            TypeRegistry typeRegistry,
            Map<String, Class<?>> fieldToClass,
            ObjectMapper mapper )
    {
        this .fieldResolver = fieldResolver;
        this.typeRegistry = typeRegistry;
        this.fieldToClass = fieldToClass;
        this.mapper = mapper;
    }

    /**
     * Traverserar källnoden rekursivt och producerar ett JSON-LD-taggat träd.
     *
     * @param source källnod
     * @param declaredClass deklarerad Java-klass för noden
     * @return JSON-LD kompatibel {@link ObjectNode}
     * @throws JsonLdMappingException om ett objekt eller arrayfält saknar mapping
     */
    public ObjectNode traverse(JsonNode source, Class<?> declaredClass) {
        ObjectNode out = mapper.createObjectNode();
        boolean isGraphNode = isGraphNode(source, declaredClass);

        if (isGraphNode) {
            Class<?> actualClass = resolveActualClass(source, declaredClass);
            String uuid = source.get("id").asText();

            out.put(JsonLdKeys.ID, JsonLdPrefix.META.apply(uuid));
            out.put(JsonLdKeys.TYPE, JsonLdPrefix.DOMAIN.apply(actualClass.getSimpleName()));
            out.put(JsonLdKeys.TYPE_ID, typeRegistry.typeIdForClass(actualClass));
        }

        source.properties().forEach(entry -> {
            String rawKey = entry.getKey();
            JsonNode value = entry.getValue();

            if (JsonLdKeys.VARIANT.equals(rawKey)) return;

            String mappedKey = fieldResolver.resolve(rawKey);
            Class<?> nested = fieldToClass.getOrDefault(rawKey, Object.class);

            if (value.isObject()) {
                out.set(mappedKey, traverse(value, nested));
            }
            else if (value.isArray()) {
                out.set(mappedKey, traverseArray(value, nested));
            }
            else {
                out.set(mappedKey, value);
            }

        });

        return out;
    }

    private boolean isGraphNode(JsonNode source, Class<?> declaredClass) {
        if (!source.has("id")) return false;
        return typeRegistry.isRegistered(declaredClass);
    }

    private Class<?> resolveActualClass(JsonNode source, Class<?> declaredClass) {
        JsonNode variantNode = source.get(JsonLdKeys.VARIANT);
        if (variantNode == null || variantNode.isNull()) {
            return declaredClass;
        }
        String variant = variantNode.asText();
        if (variant.isBlank()) {
            return declaredClass;
        }
        Class<?> resolved = typeRegistry.classForVariantName(variant);
        return resolved != null ? resolved : declaredClass;
    }

    private ArrayNode traverseArray(JsonNode array, Class<?> nestedClass) {
        ArrayNode out = mapper.createArrayNode();
        array.forEach(item -> {
            if (item.isObject()) out.add(traverse(item, nestedClass));
            else out.add(item);
        });
        return out;
    }
}
