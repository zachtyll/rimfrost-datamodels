package se.fk.mimer.datamodel.v1.exceptions;

import java.io.Serial;

/**
 * Thrown when a domain rule is violated and the model would enter
 * an invalid business state.
 * <p>
 * Used for business logic consistency errors inside the domain model.
 * Not used for decoding or bean validation errors.
 */
public class DomainInvariantException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = -4537095190283758464L;

    /**
     * @param message Error message
     */
    public DomainInvariantException( String message )
    {
        super( message );
    }

    /**
     * @param message Error message
     * @param cause   Cause of the exception
     */
    public DomainInvariantException( String message, Throwable cause )
    {
        super( message, cause );
    }

    /**
     * @param cause Cause of the exception
     */
    public DomainInvariantException( Throwable cause )
    {
        super( cause );
    }

    /**
     * @param message            Error message
     * @param cause              Cause of the exception
     * @param enableSuppression  Boolean to allow supression
     * @param writableStackTrace Boolean to allow writing stacktrace
     */
    public DomainInvariantException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
    {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}

