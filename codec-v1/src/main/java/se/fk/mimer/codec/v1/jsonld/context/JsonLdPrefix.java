package se.fk.mimer.codec.v1.jsonld.context;

/**
 * JSON-LD namespace-prefix.
 */
public enum JsonLdPrefix {
    // Metadata om noden, identitet, typ och teknisk livscykel
    META("meta"),
    // Generiska och återanvändbara datatyper
    COMMON("common"),
    // Verksamhetsdata - den faktiska domäninformationen i modellen
    DOMAIN("fk");

    private final String prefix;

    JsonLdPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String apply(String fieldName) {
        return prefix + ":" + fieldName;
    }

    public String value() {
        return prefix;
    }
}
