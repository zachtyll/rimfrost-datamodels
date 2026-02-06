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
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.StatligtStod;

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
    void StatligtStodIsValid( StatligtStod statligtStod, int expectedResult) {
        Set<ConstraintViolation<StatligtStod>> violations = validator.validate(statligtStod);
        assertEquals( expectedResult, violations.size() );
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
        Period period = TestObjectUtil.getPeriod();

        return Stream.of(
                Arguments.of(new StatligtStod(), 1),
                Arguments.of(new StatligtStod(null, 0, null, null), 1),
                Arguments.of(new StatligtStod(uuid, 0, null, null), 0),
                Arguments.of(new StatligtStod(uuid, 0, null, null), 0),
                Arguments.of(new StatligtStod(uuid, 0, period, null), 0),
                Arguments.of(new StatligtStod(uuid, 0, period, "Teststod"), 0)
        );
    }
}