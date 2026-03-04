package se.fk.mimer.codec.v2.jsonld;

/**
 * Konstantnycklar som används i JSON-LD-payloadens struktur.
 * <p>
 * Notera:
 * - @context och @type är JSON-LD-standardnycklar.
 * - data, rawData och variant är en del av vårt transportkontrakt.
 */
public final class JsonLdKeys
{
    public static final String CONTEXT = "@context";
    public static final String TYPE = "@type";
    public static final String DATA = "data";
    public static final String RAW_DATA = "rawData";
    public static final String VARIANT = "variant";
    private JsonLdKeys() {}
}
