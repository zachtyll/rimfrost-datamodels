package se.fk.mimer.codec.v1.jsonld.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import se.fk.mimer.codec.v1.jsonld.context.ContextProvider;

/**
 * Bygger {@code @context}-noden.
 */
public class ContextBuilder {

    private final ContextProvider contextProvider;
    private final ObjectMapper mapper;

    public ContextBuilder(ContextProvider contextProvider, ObjectMapper mapper) {
        this.contextProvider = contextProvider;
        this.mapper = mapper;
    }

    public ObjectNode build() {
        ObjectNode contextNode = mapper.createObjectNode();
        contextProvider.contexts().forEach(contextNode::put);
        return contextNode;
    }
}
