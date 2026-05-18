package se.fk.mimer.datamodel.v1.yrkande;

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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createYrkande;

public class YrkandeValidationTest
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
    void valid_yrkan_hasNoViolations() {
        Yrkande yrkande = createYrkande();
        var violations = validator.validate( yrkande );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }
}
