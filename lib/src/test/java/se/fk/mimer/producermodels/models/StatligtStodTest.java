package se.fk.mimer.producermodels.models;

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
import se.fk.mimer.producermodels.DataObject;
import se.fk.mimer.producermodels.Period;
import se.fk.mimer.producermodels.StatligtStod;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;


class StatligtStodTest {
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag("StatligtStodValidationTest")
    @MethodSource("provideStatligtStodTestData")
    void StatligtStodIsValid(StatligtStod StatligtStodImpl, int expectedResult) {
        Set<ConstraintViolation<StatligtStod>> violations = validator.validate(StatligtStodImpl);
        assertEquals(violations.size(), expectedResult);
    }

    @Test
    @Tag( "StatligtStodSerializationTest" )
    void StatligtStodIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/statligtstod.json" );
        DataObject dataObject = mapper.readValue( data, StatligtStod.class );
        assertInstanceOf( StatligtStod.class, dataObject );
    }

    private static Stream<Arguments> provideStatligtStodTestData() {
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        ZonedDateTime from = ZonedDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime to = ZonedDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneId.systemDefault());

        return Stream.of(
                Arguments.of(new StatligtStod(null, 0, null, null), 3),
                Arguments.of(new StatligtStod(uuid, 0, null, null), 2),
                Arguments.of(new StatligtStod(uuid, 0, null, null), 2),
                Arguments.of(new StatligtStod(uuid, 0, new Period(from, to), null), 1),
                Arguments.of(new StatligtStod(uuid, 0, new Period( from, to ), "Type1"), 0)
        );
    }
}