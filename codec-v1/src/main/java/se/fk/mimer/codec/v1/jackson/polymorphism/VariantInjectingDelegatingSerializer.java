package se.fk.mimer.codec.v1.jackson.polymorphism;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
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
        String variant = validateValueObject( value, gen );
        TokenBuffer buffer = new TokenBuffer( gen.getCodec(), false );
        delegate.serialize( value, buffer, serializers );
        parsePojoToTree( value, buffer, gen, variant );
    }

    @Override
    public void serializeWithType( Object value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException
    {
        String variant = validateValueObject( value, gen );
        TokenBuffer buffer = new TokenBuffer( gen.getCodec(), false );
        delegate.serializeWithType( value, buffer, serializers, typeSer );
        parsePojoToTree( value, buffer, gen, variant );
    }

    private String validateValueObject( Object value, JsonGenerator gen ) throws IOException
    {
        if (value == null) {
            gen.writeNull();
            throw new IOException( "Object value was null" );
        }

        String variant = variantRegistry.variantForClass( value.getClass() );
        if( variant == null || variant.isBlank()) {
            throw new DecodeException( "No variant mapping for class: " + value.getClass().getName());
        }
        return variant;
    }

    private void parsePojoToTree( Object value, TokenBuffer buffer, JsonGenerator gen, String variant ) throws IOException
    {
        JsonParser parser = buffer.asParser( gen.getCodec() );
        JsonNode node = parser.readValueAsTree();

        if( !(node instanceof ObjectNode serializedPojoNode ) )
        {
            throw new EncodeException( "Polymorphic value must serialize to JSON object: " + value.getClass().getName() );
        }

        serializedPojoNode.put( JsonLdKeys.VARIANT, variant );
        gen.writeTree( serializedPojoNode );
    }
}
