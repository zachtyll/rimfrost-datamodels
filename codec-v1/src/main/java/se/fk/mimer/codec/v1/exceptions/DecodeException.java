package se.fk.mimer.codec.v1.exceptions;

import java.io.Serial;

/**
 * Exception som indikerar att ett fel uppstod vid avkodning (decode) av payload.
 */
public class DecodeException extends CodecException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public DecodeException( String message )
    {
        super( message );
    }

    public DecodeException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
