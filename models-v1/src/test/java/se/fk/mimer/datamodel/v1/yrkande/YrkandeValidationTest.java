package se.fk.mimer.datamodel.v1.yrkande;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YrkandeValidationTest
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
    void valid_yrkan_hasNoViolations() {
        Yrkande yrkande = YrkandeFixtures.valid();
        var violations = validator.validate( yrkande );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }


}
