package se.fk.mimer.codec.v2.jackson.polymorphism;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import se.fk.mimer.codec.v2.exceptions.DecodeException;
import se.fk.mimer.codec.v2.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v2.registry.VariantRegistry;

import java.io.IOException;

@RequiredArgsConstructor
public class VariantBasedDeserializer<T> extends JsonDeserializer<T>
{
    private final Class<T> baseType;
    private final VariantRegistry variantRegistry;


    @Override
    public T deserialize( JsonParser p, DeserializationContext ctxt ) throws IOException, JacksonException
    {
        JsonNode node = p.readValueAsTree();
        if (!(node instanceof ObjectNode objectNode)) {
            throw new DecodeException( "Polymorphic node must be a JSON object for type: " + baseType.getSimpleName());

        }

        JsonNode variantNode = objectNode.get( JsonLdKeys.VARIANT );
        if (variantNode == null || variantNode.isNull()) {
            throw new DecodeException( "Missing '" + JsonLdKeys.VARIANT + "' for polymorphic type: " + baseType.getSimpleName());
        }

        String variant = variantNode.asText();
        if (variant == null || variant.isBlank()) {
            throw new DecodeException( "Blank '" + JsonLdKeys.VARIANT + "' for polymorphic type: " + baseType.getSimpleName());
        }

        Class<? extends T> impl = variantRegistry.classForVariant( variant, baseType );
        if (impl == null) {
            throw new DecodeException( "Unknown variant: " + variant + " for base type: " + baseType.getSimpleName());
        }

        objectNode.remove( JsonLdKeys.VARIANT );

        ObjectCodec codec = p.getCodec();
        if (!(codec instanceof ObjectMapper mapper)) {
            throw new IOException("Expected ObjectMapper codec for VariantBasedDeserializer of " + baseType.getName());
        }

        return mapper.treeToValue( objectNode, impl );
    }
}
