package se.fk.mimer.datamodel.v1.bidragssparr;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.BidragssparrFixtures;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.bidragssparr.Bidragssparr;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BidragssparrValidationTest
{
    private static ValidatorFactory factory;
    private static Validator validator;
    ObjectMapper mapper = new ObjectMapper()
            .registerModule( new JavaTimeModule() )
            .registerModule( new Jdk8Module() )
            .enable( DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY )
            .configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false )
            .disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );

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
