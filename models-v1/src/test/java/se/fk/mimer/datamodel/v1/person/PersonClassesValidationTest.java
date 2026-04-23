package se.fk.mimer.datamodel.v1.person;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createFysiskPerson;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createPerson;

public class PersonClassesValidationTest
{
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        factory.close();
    }

    @Test
    void valid_person_hasNoViolations() {
        Person person = createPerson();
        var violations = validator.validate( person );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

    @Test
    void valid_fysiskPerson_hasNoViolations()
    {
        FysiskPerson person = createFysiskPerson();
        var violations = validator.validate( person );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }
}
