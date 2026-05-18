package se.fk.mimer.datamodel.v1.ersattning;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.ErsattningFixtures;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill.ersattning.Ersattning;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErsattningValidationTest
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
    void valid_ersattning_hasNoViolations() {
        Ersattning ersattning = ErsattningFixtures.createErsattning();
        var violations = validator.validate( ersattning );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }
}
