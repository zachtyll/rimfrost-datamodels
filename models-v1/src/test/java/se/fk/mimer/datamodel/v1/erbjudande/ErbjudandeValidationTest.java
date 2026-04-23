package se.fk.mimer.datamodel.v1.erbjudande;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.ErbjudandeFixtures;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudande;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudandetyp;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErbjudandeValidationTest
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
    void valid_erbjudandetyp_hasNoViolations()
    {
        Erbjudandetyp typ = ErbjudandeFixtures.createErbjudandetyp();
        var violations = validator.validate( typ );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }

    @Test
    void valid_erbjudande_hasNoViolations()
    {
        Erbjudande erbjudande = ErbjudandeFixtures.createErbjudande();
        var violations = validator.validate( erbjudande );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }
}
