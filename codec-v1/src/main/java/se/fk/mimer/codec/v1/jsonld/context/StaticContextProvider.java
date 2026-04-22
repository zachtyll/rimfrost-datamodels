package se.fk.mimer.codec.v1.jsonld.context;

import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;

import java.util.Map;

/**
 * Statisk Implementation av {@link ContextProvider}
 *
 */
public class StaticContextProvider implements ContextProvider {
    private final Map<String, String> contextEntries;

    public StaticContextProvider() {
        this.contextEntries = Map.of(
                JsonLdKeys.VOCAB, "https://data.fk.se/vocab/",
                JsonLdPrefix.META.value(), "https://data.fk.se/meta/",
                JsonLdPrefix.COMMON.value(), "https://data.fk.se/common/",
                JsonLdPrefix.DOMAIN.value(), "https://data.fk.se/fk/"
        );
    }

    @Override
    public Map<String, String> contexts() {
        return contextEntries;
    }
}
