package se.fk.mimer.codec.v2.exceptions;

import java.io.Serial;

/**
 * Exception som indikerar att ett fel uppstod vid kodning (encode) av payload.
 */
public class EncodeException extends CodecException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public EncodeException( String message )
    {
        super( message );
    }

    public EncodeException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
