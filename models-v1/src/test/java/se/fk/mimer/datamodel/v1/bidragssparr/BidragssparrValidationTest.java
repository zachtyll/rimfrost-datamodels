package se.fk.mimer.datamodel.v1.bidragssparr;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.BidragssparrFixtures;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr.bidragssparr.Bidragssparr;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BidragssparrValidationTest
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
    void valid_bidragssparr_hasNoViolations() {
        Bidragssparr sparr = BidragssparrFixtures.createBidragssparr();
        var violations = validator.validate( sparr );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }
}
