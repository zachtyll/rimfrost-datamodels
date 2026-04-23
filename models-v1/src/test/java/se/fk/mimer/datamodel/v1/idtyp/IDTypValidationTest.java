package se.fk.mimer.datamodel.v1.idtyp;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.IDTyp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createIdTyp;

public class IDTypValidationTest
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
    void valid_idtyp_hasNoViolations() {
        IDTyp idtyp = createIdTyp();
        var violations = validator.validate( idtyp );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }
}
