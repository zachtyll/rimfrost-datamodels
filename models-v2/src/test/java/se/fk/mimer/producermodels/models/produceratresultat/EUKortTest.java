package se.fk.mimer.producermodels.models.produceratresultat;

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
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.EUKort;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class EUKortTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "EUKortTest" )
    @MethodSource( "provideEUKortTestData")
    void EUKortIsValid( EUKort eukort, int expectedResult )
    {
        Set<ConstraintViolation<EUKort>> violations = validator.validate( eukort );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "EUKortSerializationTest" )
    void EUKortIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/eukort.json" );
        DataObject dataObject = mapper.readValue( data, EUKort.class );
        assertInstanceOf( EUKort.class, dataObject );
    }

    private static Stream<Arguments> provideEUKortTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        ZonedDateTime ts = TestObjectUtil.getDate();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new EUKort(), 4),
                Arguments.of(new EUKort(null, null, 0, null, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new EUKort(uuid, null, 0, null, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new EUKort(uuid, kbhid, 0, null, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", null, null, null, null, null, null, null, null), 1),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, null, null, null, null, null, null, null), 0),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, period, null, null, null, null, null, null), 0),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, period, "testtyp", null, null, null, null, null), 0),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, period, "testtyp", "teststatus", null, null, null, null), 0),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, period, "testtyp", "teststatus", "Försäkringskassan", null, null, null), 0),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, period, "testtyp", "teststatus", "Försäkringskassan", "Beskrivning av intyg", null, null), 0),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, period, "testtyp", "teststatus", "Försäkringskassan", "Beskrivning av intyg", ts, null), 0),
                Arguments.of(new EUKort(uuid, kbhid, 0, "EUKort", person, period, "testtyp", "teststatus", "Försäkringskassan", "Beskrivning av intyg", ts, "11223344"), 0)
        );
    }
}
