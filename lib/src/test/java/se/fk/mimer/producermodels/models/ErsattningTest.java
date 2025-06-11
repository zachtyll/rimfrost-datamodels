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
import se.fk.mimer.producermodels.Ersattning;
import se.fk.mimer.producermodels.Period;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class ErsattningTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag("ErsattningValidationTest")
    @MethodSource("provideErsattningTestData")
    void ersattningIsValid( Ersattning ersattning, int expectedResult) {
        Set<ConstraintViolation<Ersattning>> violations = validator.validate( ersattning );
        assertEquals( violations.size(), expectedResult );
    }


    @Test
    @Tag( "ErsattningSerializationTest" )
    void ErsattningIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/ersattning.json" );
        DataObject dataObject = mapper.readValue( data, Ersattning.class );
        assertInstanceOf( Ersattning.class, dataObject );
    }

    private static Stream<Arguments> provideErsattningTestData() {
        ZonedDateTime date = ZonedDateTime.of(2024, 1, 1, 10, 0, 0, 0, ZoneId.systemDefault());
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        BigDecimal amount = new BigDecimal("1000.00");
        double omfattning = 0.0;
        new Ersattning();
        return Stream.of(
                Arguments.of(new Ersattning(), 11),
                Arguments.of(new Ersattning( null, null, 0, null, null, null, null, null, null, omfattning, null, null, null, null, null), 11),
                Arguments.of(new Ersattning( uuid, null, 0, null, null, null, null, null, null, omfattning, null, null, null, null, null), 10),
                Arguments.of(new Ersattning( uuid, uuid, 0, null, null, null, null, null, null, omfattning, null, null, null, null, null) , 9),
                Arguments.of(new Ersattning( uuid, uuid, 0, null, null, null, null, null, null,omfattning, null, null, null, null, null) , 9),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", null, "type", null, null, null, omfattning, null, null, null, null, null) , 7),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", null, null, null, omfattning, null, null, null, null, null) , 6),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, null, null, omfattning, null, null, null, null, null) , 5),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", null, omfattning, null, null, null, null, null) , 4),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, null, null, null, null, null) , 3),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, null, null, null, null, null) , 3),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, null, null, null, null, null) , 3),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, "periodisering", null, null, null, null) , 2),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, "periodisering", "andringsorsak", null, null, null) , 2),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, "periodisering", "andringsorsak", "avslagsanledning", null, null) , 2),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, "periodisering", "andringsorsak", "avslagsanledning", "status", null) , 1),
                Arguments.of(new Ersattning( uuid, uuid, 0, "person", new Period( date, date.plusDays( 5 )), "type", date, "typ", amount, omfattning, "periodisering", "andringsorsak", "avslagsanledning", "status", "berakningsgrund") , 0)
        );
    }
}
