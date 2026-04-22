package se.fk.mimer.codec.v1.exceptions;

import se.fk.mimer.codec.v1.jsonld.builder.NodeTraverser;

import java.io.Serial;

/**
 * Kastas när {@link NodeTraverser} stöter på
 * ett objekt eller arrayfält som saknar klassregistrering.
 *
 */
public class JsonLdMappingException extends CodecException {
    @Serial
    private static final long serialVersionUID = 1L;

    public JsonLdMappingException(String message) {
        super(message);
    }

    public JsonLdMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
