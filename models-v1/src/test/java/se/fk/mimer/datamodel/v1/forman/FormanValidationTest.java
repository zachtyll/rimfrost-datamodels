package se.fk.mimer.datamodel.v1.forman;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.FormanFixtures;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormanValidationTest
{
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setupValidator()
    {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown()
    {
        factory.close();
    }

    @Test
    void valid_formanstyp_hasNoViolations()
    {
        Formanstyp typ = FormanFixtures.createFormanstyp();
        var violations = validator.validate( typ );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }

    @Test
    void valid_forman_hasNoViolations()
    {
        Forman forman = FormanFixtures.createForman();
        var violations = validator.validate( forman );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }
}
