package se.fk.mimer.codec.v1.jackson.polymorphism;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.exceptions.EncodeException;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v1.registry.VariantRegistry;

import java.io.IOException;

public class VariantInjectingDelegatingSerializer extends JsonSerializer<Object>
{
    private final JsonSerializer<Object> delegate;
    private final VariantRegistry variantRegistry;

    public VariantInjectingDelegatingSerializer( JsonSerializer<Object> delegate,
                                                 VariantRegistry variantRegistry) {
        this.delegate = delegate;
        this.variantRegistry = variantRegistry;
    }

    @Override
    public void serialize( Object value, JsonGenerator gen, SerializerProvider serializers ) throws IOException
    {
        if (value == null) {
            gen.writeNull();
            return;
        }

        String variant = variantRegistry.variantForClass( value.getClass() );
        if( variant == null || variant.isBlank()) {
            throw new DecodeException( "No variant mapping for class: " + value.getClass().getName());
        }

        // Delegate to REAL default serializer for the conrete type into a buffer
        TokenBuffer buffer = new TokenBuffer( gen.getCodec(), false );
        delegate.serialize( value, buffer, serializers );

        // Convert buffered JSON into ObjectNode so we can inject "variant"
        JsonParser parser = buffer.asParser(gen.getCodec());
        JsonNode node = parser.readValueAsTree();

        if (!(node instanceof ObjectNode serializedPojoNode )) {
            throw new EncodeException( "Polymorhic value must serialize to JSON object: " + value.getClass().getName() );
        }

        serializedPojoNode.put( JsonLdKeys.VARIANT, variant );
        gen.writeTree( serializedPojoNode );

    }
}
