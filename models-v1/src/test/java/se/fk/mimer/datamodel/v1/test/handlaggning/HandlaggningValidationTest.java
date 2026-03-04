package se.fk.mimer.datamodel.v1.test.handlaggning;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.HandlaggningFixtures;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.utils.FixtureUtil;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandlaggningValidationTest
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
    void valid_handlaggning_hasNoViolations() {
        // Arrange
        Handlaggning handlaggning = HandlaggningFixtures.valid();
        handlaggning.setAvslutad( FixtureUtil.fixedDate() );
        handlaggning.setSkapad( FixtureUtil.fixedDate() );
        handlaggning.setArendeId( FixtureUtil.newId().toString() );

        var violations = validator.validate( handlaggning );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

}
