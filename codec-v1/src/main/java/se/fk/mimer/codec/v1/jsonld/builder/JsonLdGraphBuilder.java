package se.fk.mimer.codec.v1.jsonld.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;

/**
 * Bygger JSON-LD {@code @graph}-format.
 */
public class JsonLdGraphBuilder implements JsonLdPayloadBuilder {

    private final ContextBuilder contextBuilder;
    private final NodeTraverser nodeTraverser;
    private final ObjectMapper mapper;

    public JsonLdGraphBuilder(
            ContextBuilder contextBuilder,
            NodeTraverser nodeTraverser,
            ObjectMapper mapper)
    {
        this.contextBuilder = contextBuilder;
        this.nodeTraverser = nodeTraverser;
        this.mapper = mapper;
    }

    @Override
    public ObjectNode build(
            ObjectNode dataNode,
            ObjectNode rawDataNode,
            Class<?> dataClass,
            String producerId,
            String modelVersion)
    {
        validate(dataNode, rawDataNode, producerId, modelVersion);

        ObjectNode root = mapper.createObjectNode();
        ArrayNode graph = mapper.createArrayNode();

        root.set(JsonLdKeys.CONTEXT, contextBuilder.build());
        graph.add(nodeTraverser.traverse(dataNode, dataClass));
        root.set(JsonLdKeys.GRAPH, graph);
        root.set(JsonLdKeys.RAW_DATA, rawDataNode);

        return root;
    }

    private static void validate(
            ObjectNode dataNode,
            ObjectNode rawDataNode,
            String producerId,
            String modelVersion
    ) {
        if (dataNode == null || rawDataNode == null) {
            throw new IllegalArgumentException(
                    "dataNode and rawDataNode must not be null"
            );
        }
        if (producerId == null || producerId.isBlank()) {
            throw new IllegalArgumentException(
                    "producerId must not be blank"
            );
        }
        if (modelVersion == null || modelVersion.isBlank()) {
            throw new IllegalArgumentException(
                    "modelVersion must not be blank"
            );
        }
    }
}
