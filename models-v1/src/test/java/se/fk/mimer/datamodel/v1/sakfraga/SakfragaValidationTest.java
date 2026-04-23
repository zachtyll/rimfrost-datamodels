package se.fk.mimer.datamodel.v1.sakfraga;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.SakfragaFixtures;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfragetyper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SakfragaValidationTest
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
    void valid_sakfragatyper_hasNoViolations() {
        Sakfragetyper typ = SakfragaFixtures.createSakfragetyp();
        var violations = validator.validate( typ );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

    @Test
    void valid_sakfraga_hasNoViolations() {
        Sakfraga sakfraga = SakfragaFixtures.createSakfraga();
        var violations = validator.validate( sakfraga );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }
}
