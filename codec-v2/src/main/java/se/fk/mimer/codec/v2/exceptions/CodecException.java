package se.fk.mimer.codec.v2.exceptions;

import java.io.Serial;

/**
 * Bas-exception för all fel relaterade till codec.
 *
 * <p>
 * Används som gemensam superklass för encode och decode fel så att anropande kod
 * kan fånga alla codec-relaterade fel via en gemensam typ om så önskas.
 */
public class CodecException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public CodecException(String message) {
        super(message);
    }

    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }
}
