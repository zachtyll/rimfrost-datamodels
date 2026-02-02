package se.fk.mimer.producermodels.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.mimer.producermodels.FysiskPerson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class FysiskPersonValidatorTest {

    private FysiskPersonValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new FysiskPersonValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    void validPerson() {
        FysiskPerson person = FysiskPerson.builder()
                .personnummer("2000-01-01-1234")
                .kundid("1234")
                .build();

        assertTrue(validator.isValid(person, context));
    }

    @Test
    void personOnlyPnr() {
        FysiskPerson person = FysiskPerson.builder()
                .personnummer("2000-01-01-1234")
                .kundid(null)
                .build();

        assertTrue(validator.isValid(person, context));
    }

    @Test
    void personWithIDOnly() {
        FysiskPerson person = FysiskPerson.builder()
                .personnummer(null)
                .kundid("1234")
                .build();

        assertTrue(validator.isValid(person, context));
    }

    @Test
    void personEmptyNumberAndNullId() {
        FysiskPerson customer = FysiskPerson.builder()
                .personnummer("")
                .kundid(null)
                .build();

        assertTrue(validator.isValid(customer, context));
    }

    @Test
    void personhEmptyOptionalNumberAndNullId() {
        FysiskPerson customer = FysiskPerson.builder()
                .personnummer(null) // Missing number
                .kundid(null)
                .build();

        assertTrue(validator.isValid(customer, context));
    }
}
