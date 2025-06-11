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
import se.fk.mimer.producermodels.Kontouppgift;
import se.fk.mimer.producermodels.Period;
import se.fk.mimer.producermodels.Utforare;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class UtforareTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest()
    @Tag("UtforareValidationTest")
    @MethodSource("provideUtforareTestData")
    void UtforareIsValid(Utforare utforareImpl, int expectedResult) {
        Set<ConstraintViolation<Utforare>> violations = validator.validate(utforareImpl);
        System.out.println(violations.size());
        assertEquals( violations.size(), expectedResult );
    }

    @Test
    @Tag( "UtforareSerializationTest" )
    void UtforareIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/utforare.json" );
        DataObject dataObject = mapper.readValue( data, Utforare.class );
        assertInstanceOf( Utforare.class, dataObject );
    }

    private static Stream<Arguments> provideUtforareTestData() {
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        ZonedDateTime date = ZonedDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        Kontouppgift validKontoUtforare = new Kontouppgift("typ789", "nr0123");
        Kontouppgift validKontoBetaltjanst = new Kontouppgift( "typ789", "nr0123");

        return Stream.of(
                Arguments.of(new Utforare(), 7),
                Arguments.of(new Utforare(null, null, 0, null, null, null, null, null, null), 7),
                Arguments.of(new Utforare(uuid, null, 0, null, null, null, null, null, null), 6),
                Arguments.of(new Utforare(uuid, uuid, 0, null, null, null, null, null, null), 5),
                Arguments.of(new Utforare(uuid, uuid, 0, null, null, null, null, null, null), 5),
                Arguments.of(new Utforare(uuid, uuid, 0, "avserPerson", null, null, null, null, null), 4),
                Arguments.of(new Utforare(uuid, uuid, 0, "avserPerson", new Period( date, date.plusDays( 5 ) ), "typ", "status", null, null), 1),
                Arguments.of(new Utforare(uuid, uuid, 0, "avserPerson", new Period( date, date.plusDays( 5 ) ), "typ", "status", List.of(validKontoUtforare), List.of(validKontoBetaltjanst) ), 0),
                // Valid with kontoUtforare
                Arguments.of(new Utforare(uuid, uuid, 0, "avserPerson", new Period( date, date.plusDays( 5 ) ), "typ", "status", List.of(validKontoUtforare), null), 0),
                // Valid with kontoBetaltjanst but No konroUtforare
                Arguments.of(new Utforare(uuid, uuid, 0, "avserPerson", new Period( date, date.plusDays( 5 ) ), "typ", "status", null, List.of(validKontoBetaltjanst)), 1),
                Arguments.of(new Utforare(uuid, uuid, 0, "avserPerson", new Period( date, date.plusDays( 5 ) ), "typ", "status", Collections.emptyList(), Collections.emptyList() ), 1)
        );
    }
}