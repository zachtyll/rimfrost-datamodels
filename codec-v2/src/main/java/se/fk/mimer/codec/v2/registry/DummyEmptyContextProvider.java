package se.fk.mimer.codec.v2.registry;

import java.util.List;

/**
 * This is a dummy provider which returns a empty list of context data.
 * Until we either provide some faked context data
 * or can inject a real context provider implementation to the codec in the future.
 */
public final class DummyEmptyContextProvider implements ContextProvider
{
    @Override
    public List<String> contextsFor( String producerId, String modelVersion, String typeIri )
    {
        return List.of();
    }
}
