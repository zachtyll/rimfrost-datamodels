package se.fk.mimer.datamodel.v1.person;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createFysiskPerson;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createPersontyp;

public class PersontypClassesValidationTest
{
    private static ValidatorFactory factory;
    private static Validator validator;
    ObjectMapper mapper = new ObjectMapper()
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
    void valid_person_hasNoViolations() {
        Persontyp persontyp = createPersontyp();
        var violations = validator.validate( persontyp );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

    @Test
    void valid_fysiskPerson_hasNoViolations()
    {
        FysiskPerson person = createFysiskPerson();
        var violations = validator.validate( person );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }
}
