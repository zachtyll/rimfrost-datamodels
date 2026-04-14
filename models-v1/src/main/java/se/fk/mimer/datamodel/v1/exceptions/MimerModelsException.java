package se.fk.mimer.datamodel.v1.exceptions;

import java.io.Serial;

/**
 * Bas-exception för all fel relaterade till modellerna.
 */
public class MimerModelsException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public MimerModelsException(String message) {
        super(message);
    }
}
