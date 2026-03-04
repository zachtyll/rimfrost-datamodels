package se.fk.mimer.codec.v2.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Objects;
import java.util.Set;

public class ContractValidator
{
    private final Validator validator;

    public ContractValidator(Validator validator) {
        this.validator = Objects.requireNonNull(validator, "validator must not be null");
    }

    public void validate(Object object) {
        Objects.requireNonNull( object, "object must not be null" );

        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ContractValidationException( violations );
        }
    }
}
