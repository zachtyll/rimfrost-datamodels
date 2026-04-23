package se.fk.mimer.datamodel.v1.rolliyrkande;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollerIYrkande;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createRollIYrkande;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createRollerIYrkande;

public class RollClassesValidationTest
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
    void valid_rollIYrkande_hasNoViolations()
    {
        RollIYrkande roll = createRollIYrkande();
        var violations = validator.validate( roll );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }

    @Test
    void valid_rollerIYrkande_hasNoViolations()
    {
        RollerIYrkande roller = createRollerIYrkande();
        var violations = validator.validate( roller );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }
}
