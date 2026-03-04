package se.fk.mimer.producermodels.exceptions;

import java.io.Serial;

/**
 * Custom exception for Mimer
 */
public class MimerException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = -4537095190283758464L;

    /**
     * @param message Error message
     */
    public MimerException( String message )
    {
        super( message );
    }

    /**
     * @param message Error message
     * @param cause   Cause of the exception
     */
    public MimerException( String message, Throwable cause )
    {
        super( message, cause );
    }

    /**
     * @param cause Cause of the exception
     */
    public MimerException( Throwable cause )
    {
        super( cause );
    }

    /**
     * @param message            Error message
     * @param cause              Cause of the exception
     * @param enableSuppression  Boolean to allow supression
     * @param writableStackTrace Boolean to allow writing stacktrace
     */
    public MimerException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
    {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}

