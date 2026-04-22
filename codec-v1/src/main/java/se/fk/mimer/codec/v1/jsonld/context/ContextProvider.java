package se.fk.mimer.codec.v1.jsonld.context;

import java.util.Map;

/**
 * Provider för JSON-LD contexts ({@code @context})
 */
public interface ContextProvider
{

    /**
     * Returnerar contexts.
     */
    Map<String, String> contexts();
}
