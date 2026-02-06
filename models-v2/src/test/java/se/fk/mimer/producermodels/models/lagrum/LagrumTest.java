package se.fk.mimer.producermodels.models.lagrum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.lagrum.Lagrum;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class LagrumTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "LagrumValidationTest" )
    @MethodSource("provideLagrumTestData")
    void lagrumIsValid( Lagrum lagrum, int expectedResult )
    {
        Set<ConstraintViolation<Lagrum>> violations = validator.validate( lagrum );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "LagrumSerializationTest" )
    void LagrumIsSeriaziable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/lagrum.json" );
        DataObject dataObject = mapper.readValue( data, Lagrum.class );
        assertInstanceOf( Lagrum.class, dataObject );
    }

    private static Stream<Arguments> provideLagrumTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        Period period = TestObjectUtil.getPeriod();

        return Stream.of(
                Arguments.of(new Lagrum(), 5),
                Arguments.of(new Lagrum(null, 0, null, null, null, null, null, null), 5),
                Arguments.of(new Lagrum(uuid, 0, null, null, null, null, null, null), 4),
                Arguments.of(new Lagrum(uuid, 0, null, null, null, null, null, null), 4),
                Arguments.of(new Lagrum(uuid, 0, "Brottsbalken", null, null, null, null, null), 3),
                Arguments.of(new Lagrum(uuid, 0, "Brottsbalken", "Kapitel 1", null, null, null, null), 2),
                Arguments.of(new Lagrum(uuid, 0, "Brottsbalken", "Kapitel 1", "§1", null, null, null), 1),
                Arguments.of(new Lagrum(uuid, 0, "Brottsbalken", "Kapitel 1", "§1", null, null, period), 0),
                Arguments.of(new Lagrum(uuid, 0, "Brottsbalken", "Kapitel 1", "§1", "Stycke 1", null, period), 0),
                Arguments.of(new Lagrum(uuid, 0, "Brottsbalken", "Kapitel 1", "§1", "Stycke 1", "Punkt 1", period), 0)
        );
    }
}
