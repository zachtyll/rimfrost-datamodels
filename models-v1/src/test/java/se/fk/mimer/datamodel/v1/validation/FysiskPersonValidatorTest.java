package se.fk.mimer.datamodel.v1.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;

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
                .build();

        assertTrue(validator.isValid(person, context));
    }

    @Test
    void personOnlyPnr() {
        FysiskPerson person = FysiskPerson.builder()
                .personnummer("2000-01-01-1234")
                .build();

        assertTrue(validator.isValid(person, context));
    }

    @Test
    void personEmptyNumber() {
        FysiskPerson customer = FysiskPerson.builder()
                .personnummer("")
                .build();

        assertFalse(validator.isValid(customer, context));
    }

    @Test
    void personEmptyOptionalNumber() {
        FysiskPerson customer = FysiskPerson.builder()
                .personnummer(null) // Missing number
                .build();

        assertFalse(validator.isValid(customer, context));
    }
}
