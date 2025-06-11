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
import se.fk.mimer.producermodels.Beslut;
import se.fk.mimer.producermodels.DataObject;
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

class BeslutTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag("BeslutValidationTest")
    @MethodSource("provideBeslutTestData")
    void beslutIsValid( Beslut beslut, int expectedResult) {
        Set<ConstraintViolation<Beslut>> violations = validator.validate( beslut );
        assertEquals( violations.size(), expectedResult );
    }

    @Test
    @Tag( "BeslutSerializationTest" )
    void BeslutIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/beslut.json" );
        DataObject dataObject = mapper.readValue( data, Beslut.class );
        assertInstanceOf( Beslut.class, dataObject );
    }

    private static Stream<Arguments> provideBeslutTestData() {
        ZonedDateTime date  = ZonedDateTime.of(2024, 1, 1, 10, 0, 0, 0, ZoneId.systemDefault());
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();

        return Stream.of(
                Arguments.of(new Beslut(), 8),
                Arguments.of(new Beslut(null,null, null, null, null, null, null, null, 0), 8),
                Arguments.of(new Beslut(uuid,null, null, null, null, null, null, null, 0), 7),
                Arguments.of(new Beslut(uuid, uuid, null, null, null, null, null, null, 0), 6),
                Arguments.of(new Beslut(uuid, uuid, date, null, null, null, null, null, 0), 5),
                Arguments.of(new Beslut(uuid, uuid, date, "SLUTLIGT", null, null, null, null, 0), 4),
                Arguments.of(new Beslut(uuid, uuid, date, "SLUTLIGT", "BEVILJAN", null, null, null, 0), 3),
                Arguments.of(new Beslut(uuid, uuid, date, "SLUTLIGT", "BEVILJAN", "kortnr", null, null, 0), 2),
                Arguments.of(new Beslut(uuid, uuid, date, "INTERIMISTISKT", "BEVILJAN", "kortnr", "LawReference", null, 0), 1),
                Arguments.of(new Beslut(uuid, uuid, date, "INTERIMISTISKT", "AVSLAG", "kortnr", "LawReference", "Organization", 0), 0),
                Arguments.of(new Beslut(uuid, uuid, date, "INTERIMISTISKT", "AVSLAG", "kortnr", "LawReference", "Organization", 0), 0)
        );
    }
}
