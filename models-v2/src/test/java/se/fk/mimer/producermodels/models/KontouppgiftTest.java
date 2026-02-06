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
import se.fk.mimer.producermodels.utils.TestdataUtil;
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.Kontouppgift;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class KontouppgiftTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "KontouppgiftTest" )
    @MethodSource( "provideKontouppgiftTestData")
    void KontouppgiftIsValid( Kontouppgift kontouppgift, int expectedResult )
    {
        Set<ConstraintViolation<Kontouppgift>> violations = validator.validate( kontouppgift );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "KontouppgiftSerializationTest" )
    void KontouppgiftIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/kontouppgift.json" );
        DataObject dataObject = mapper.readValue( data, Kontouppgift.class );
        assertInstanceOf( Kontouppgift.class, dataObject );
    }

    private static Stream<Arguments> provideKontouppgiftTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();

        return Stream.of(
                Arguments.of(new Kontouppgift(), 2),
                Arguments.of(new Kontouppgift(null, 0, null, null, null), 2),
                Arguments.of(new Kontouppgift(uuid, 0, null, null, null), 1),
                Arguments.of(new Kontouppgift(uuid, 0, null, null, null), 1),
                Arguments.of(new Kontouppgift(uuid, 0, "Testkonto", null, null), 1),
                Arguments.of(new Kontouppgift(uuid, 0, "Testkonto", "Testnummer", null), 1),
                Arguments.of(new Kontouppgift(uuid, 0, "Testkonto", "Testnummer", Boolean.TRUE), 0)
        );
    }
}
