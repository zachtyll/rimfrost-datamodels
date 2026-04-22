package se.fk.mimer.codec.v1.jsonld;

/**
 * Konstantnycklar som används i JSON-LD-payloadens struktur.
 * <p>
 * Notera:
 * - @context, @type, @graph, @vocab och @id är JSON-LD-standardnycklar.
 * - meta:typeId, rawData och variant är en del av vårt transportkontrakt.
 */
public final class JsonLdKeys
{
    public static final String CONTEXT = "@context";
    public static final String VOCAB = "@vocab";
    public static final String GRAPH = "@graph";
    public static final String ID = "@id";
    public static final String TYPE = "@type";
    public static final String TYPE_ID = "meta:typeId";
    public static final String RAW_DATA = "rawData";
    public static final String VARIANT = "variant";
    private JsonLdKeys() {}
}
