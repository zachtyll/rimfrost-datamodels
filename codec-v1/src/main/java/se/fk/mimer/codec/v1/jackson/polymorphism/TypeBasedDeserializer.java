package se.fk.mimer.codec.v1.jackson.polymorphism;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v1.registry.TypeRegistry;

import java.io.IOException;

/**
 * Läser JSON-LD {@code @type}, slår upp konkret klass via {@link TypeRegistry},
 * och delegerar till Jacksons inbyggda deserializer, direkt vid matchande typ,
 * annars via subklassens delegate (polymorfism).
 *
 * @param <T> värde typen som deserialiseras
 */
@RequiredArgsConstructor
public class TypeBasedDeserializer<T> extends JsonDeserializer<T> implements ResolvableDeserializer
{
    private final Class<T> baseType;
    private final TypeRegistry typeRegistry;
    private final JsonDeserializer<?> delegate;

    JsonDeserializer<?> getDelegate() {
        return delegate;
    }

    @Override
    public void resolve(DeserializationContext context) throws JsonMappingException {
        if (delegate instanceof ResolvableDeserializer resolvableDeserializer) {
            resolvableDeserializer.resolve(context);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize( JsonParser jsonParser, DeserializationContext context ) throws IOException
    {
        JsonNode node = jsonParser.readValueAsTree();
        if (!(node instanceof ObjectNode objectNode)) {
            throw new DecodeException( "Node must be a JSON object for type: " + baseType.getSimpleName());

        }

        Class<? extends T> actual = readAndResolveType(objectNode);
        JsonDeserializer<?> chosenDelegate = chooseDelegate(actual, context);

        try (JsonParser sub = objectNode.traverse(jsonParser.getCodec())) {
            sub.nextToken();
            return (T) chosenDelegate.deserialize(sub, context);
        }
    }

    /**
     * Läser och tar bort {@code type}, returnerar konkret klass validerad mot
     * {@code baseType}.
     */
    @SuppressWarnings("unchecked")
    private Class<? extends T> readAndResolveType(ObjectNode objectNode) {
        JsonNode typeNode = objectNode.remove(JsonLdKeys.TYPE);
        if (typeNode == null || typeNode.isNull() || typeNode.asText().isBlank()) {
            throw new DecodeException(
                    "Mising @type for registered type: " + baseType.getSimpleName()
            );
        }

        String shortType  = typeNode.asText();
        Class<?> resolved = typeRegistry.classForShortType(shortType);
        if (resolved == null) {
            throw new DecodeException(
                    "Unknown @type: " + shortType
                            + " for base type: " + baseType.getSimpleName()
            );
        }

        if (!baseType.isAssignableFrom(resolved)) {
            throw new DecodeException(
                    "Resolved class " + resolved.getSimpleName()
                            + " is not subtype of " + baseType.getSimpleName()
            );
        }
        return (Class<? extends T>) resolved;
    }

    /**
     * Returnerar wrapperns delegate vid matchande typ, annars subklassens delegate,
     * detta unviker att @type läses en andra gång ur en redan rensad nod.
     */
    private JsonDeserializer<?> chooseDelegate(Class<? extends T> actual, DeserializationContext context) throws JsonMappingException{
        if (actual == baseType) {
            return delegate;
        }
        JavaType javaType = context.constructType(actual);
        JsonDeserializer<Object> subDeserializer = context.findRootValueDeserializer(javaType);
        return subDeserializer instanceof TypeBasedDeserializer<?> typeBasedDeserializer ?
                typeBasedDeserializer.getDelegate() : subDeserializer;
    }
}
