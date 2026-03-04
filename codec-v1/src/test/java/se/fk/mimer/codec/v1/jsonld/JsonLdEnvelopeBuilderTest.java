package se.fk.mimer.codec.v1.jsonld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.registry.ContextProvider;
import se.fk.mimer.codec.v1.registry.TypeRegistry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JsonLdEnvelopeBuilderTest
{
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void buildRoot_tags_root_and_data_but_not_rawData() {
        TypeRegistry typeRegistry = new TypeRegistry()
        {
            @Override
            public String typeIdForClass( Class<?> clazz )
            {
                if (clazz == Object.class) {
                    return "urn:mimer:typ:Yrkande:2.0";
                }
                return null;
            }
            @Override
            public String payloadTypeId( String modelVersion )
            {
                return "urn:mimer:payload:" + modelVersion;
            }
            @Override
            public Class<?> classForTypeId( String typeId )
            {
                return Object.class;
            }
            @Override
            public String modelVersionForTypeId( String typeId )
            {
                return "2.0";
            }
        };

        ContextProvider contextProvider = ( producerId, modelVersion, typeId ) -> List.of();

        JsonLdEnvelopeBuilder builder = new JsonLdEnvelopeBuilder( typeRegistry, contextProvider );

        ObjectNode data = mapper.createObjectNode().put("a", 1);
        ObjectNode raw = mapper.createObjectNode().put("x", 2);
        ObjectNode root = mapper.createObjectNode();

        ObjectNode out = builder.buildRoot(
                data,
                Object.class,
                raw,
                "producerA",
                "2.0",
                JsonLdTaggingMode.ROOT_AND_DATA,
                root
        );

        assertEquals("urn:mimer:payload:2.0", out.get( JsonLdKeys.TYPE ).asText());
        assertEquals( "urn:mimer:typ:Yrkande:2.0", out.get( JsonLdKeys.DATA ).get(JsonLdKeys.TYPE).asText());
        assertNull(out.get( JsonLdKeys.RAW_DATA ).get( JsonLdKeys.TYPE ), "rawData must NOT be tagged");
    }
}
