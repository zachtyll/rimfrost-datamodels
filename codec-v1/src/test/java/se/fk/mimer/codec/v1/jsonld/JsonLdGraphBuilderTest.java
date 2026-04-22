package se.fk.mimer.codec.v1.jsonld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.jsonld.builder.ContextBuilder;
import se.fk.mimer.codec.v1.jsonld.builder.JsonLdGraphBuilder;
import se.fk.mimer.codec.v1.jsonld.builder.NodeTraverser;
import se.fk.mimer.codec.v1.jsonld.context.ContextProvider;
import se.fk.mimer.codec.v1.jsonld.context.StaticContextProvider;
import se.fk.mimer.codec.v1.jsonld.field.FieldNameResolver;
import se.fk.mimer.codec.v1.jsonld.field.FieldToClassMapBuilder;
import se.fk.mimer.codec.v1.registry.CodecRegistries;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonLdGraphBuilderTest {

    private JsonLdGraphBuilder builder;

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        ContextProvider contextProvider = new StaticContextProvider();
        ContextBuilder contextBuilder = new ContextBuilder(contextProvider, mapper);

        TypeRegistry typeRegistry = CodecRegistries.createTypeRegistry();
        FieldNameResolver fieldResolver = new FieldNameResolver();
        Map<String, Class<?>> fieldToClass = FieldToClassMapBuilder.build(
                typeRegistry.getRegisteredClasses()
        );
        NodeTraverser traverser = new NodeTraverser(
                fieldResolver,
                typeRegistry,
                fieldToClass,
                mapper
        );
        builder = new JsonLdGraphBuilder(
                contextBuilder,
                traverser,
                mapper
        );
    }

    @Test
    void build_produces_context_graph_and_rawData_root() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode data = mapper.createObjectNode().put("id", "abc").put("yrkan", "x");
        ObjectNode rawData = mapper.createObjectNode().put("orig", "value");

        ObjectNode root = builder.build(data, rawData, Yrkande.class, "producerX", "1,0");

        assertTrue(root.has(JsonLdKeys.CONTEXT));
        assertTrue(root.has(JsonLdKeys.GRAPH));
        assertTrue(root.get(JsonLdKeys.GRAPH).isArray());
        assertTrue(root.has(JsonLdKeys.RAW_DATA));
    }

    @Test
    void build_throws_when_dataNode_is_null() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rawData = mapper.createObjectNode();

        assertThrows(IllegalArgumentException.class, () -> builder.build(
                null,
                rawData,
                Yrkande.class,
                "p",
                "1.0")
        );
    }
}
