package se.fk.mimer.codec.v1.jsonld.builder;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Bygger JSON-LD payload från {@code dataNode} och {@code rawDataNode}.
 */
public interface JsonLdPayloadBuilder {

    /**
     * Bygger en JSON-LD payload-nod.
     *
     * @param dataNode serialiserad data
     * @param rawDataNode orörd rådata
     * @param dataClass Java-klass för {@code dataNode}
     * @param producerId producent-id
     * @param modelVersion modellversion
     * @return JSON-LD root-nod
     * @throws IllegalArgumentException om något argument är ogiltigt
     */
    ObjectNode build(
            ObjectNode dataNode,
            ObjectNode rawDataNode,
            Class<?> dataClass,
            String producerId,
            String modelVersion
    );
}
