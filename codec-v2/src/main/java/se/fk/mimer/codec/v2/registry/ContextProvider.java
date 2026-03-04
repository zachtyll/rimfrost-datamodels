package se.fk.mimer.codec.v2.registry;

import java.util.List;

/**
 * Provider för JSON-LD contexts ({@code @context}) som kan injiceras vid encode.
 *
 * <p>
 * Returnerar 0..N context-URI:er för given producerId, modelVersion och typeIri.
 * Tom lista betyder att ingen {@code @context} injiceras.
 */
public interface ContextProvider
{

    /**
     * Returnerar contexts.
     *
     * @param producerId producent-id
     * @param modelVersion modellversion
     * @param typeIri type-IRI
     * @return lista med contexts (tom lista betyder ingen context)
     */
    List<String> contextsFor( String producerId, String modelVersion, String typeIri);
}
