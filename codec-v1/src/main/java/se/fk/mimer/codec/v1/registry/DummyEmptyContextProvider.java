package se.fk.mimer.codec.v1.registry;

import se.fk.mimer.codec.v1.jsonld.context.ContextProvider;

import java.util.Map;

/**
 * This is a dummy provider which returns a empty list of context data.
 * Until we either provide some faked context data
 * or can inject a real context provider implementation to the codec in the future.
 */
public final class DummyEmptyContextProvider implements ContextProvider
{
    @Override
    public Map<String, String> contexts() {
        return Map.of();
    }
}
