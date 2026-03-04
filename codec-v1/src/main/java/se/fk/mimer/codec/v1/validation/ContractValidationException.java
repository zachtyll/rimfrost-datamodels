package se.fk.mimer.codec.v1.validation;

import jakarta.validation.ConstraintViolation;

import java.io.Serial;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ContractValidationException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;

    private final Set<? extends ConstraintViolation<?>> violations;

    public ContractValidationException( Set<? extends ConstraintViolation<?>> violations) {
        super(buildMessage(violations));
        this.violations = violations == null ? Collections.emptySet() : Collections.unmodifiableSet( violations );
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }

    private static String buildMessage(Set<? extends ConstraintViolation<?>> violations) {
        if (violations == null || violations.isEmpty()) {
            return "Contract validation exception was created without violations (this indicates a bug)";
        }

        String details = violations.stream()
                .map( v -> v.getPropertyPath()
                        + " "
                        + v.getMessage()
                        + " (invalid=" + formatInvalidValue(v.getInvalidValue()) + ")")
                .collect( Collectors.joining("\n - ", " - ", ""));

        return "Bean validation failed (" + violations.size() + " violations)\n" + details;
    }

    private static String formatInvalidValue(Object value) {
        if (value == null) {
            return "null";
        }
        String stringValue = String.valueOf( value );

        int maxLength = 120;
        if (stringValue.length() > maxLength) {
            return stringValue.substring( 0, maxLength ) + "...";
        }

        return stringValue;
    }
}
