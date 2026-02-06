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
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.Forhandstillstand;
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

class ForhandstillstandTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "ForhandstillstandTest" )
    @MethodSource( "provideForhandstillstandTestData")
    void ForhandstillstandIsValid( Forhandstillstand forhandstillstand, int expectedResult )
    {
        Set<ConstraintViolation<Forhandstillstand>> violations = validator.validate( forhandstillstand );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "ForhandstillstandSerializationTest" )
    void ForhandstillstandIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/forhandstillstand.json" );
        DataObject dataObject = mapper.readValue( data, Forhandstillstand.class );
        assertInstanceOf( Forhandstillstand.class, dataObject );
    }

    private static Stream<Arguments> provideForhandstillstandTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        ZonedDateTime ts = TestObjectUtil.getDate();
        Period vardperiod = TestObjectUtil.getForhandstillstand().getVardperiod();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new Forhandstillstand(), 4),
                Arguments.of(new Forhandstillstand(null, null, 0 ,null, null, null, null, null, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new Forhandstillstand(uuid, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, null, null, null, null, null, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", null, null, null, null, null, null, null, null, null, null, null, null), 1),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, null, null, null, null, null, null, null, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, null, null, null, null, null, null, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", null, null, null, null, null, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", null, null, null, null, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", null, null, null, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", "beskrivning av intyg", null, null, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", "beskrivning av intyg", ts, null, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", "beskrivning av intyg", ts, vardperiod, null, null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", "beskrivning av intyg", ts, vardperiod, "testbeskrivning", null, null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", "beskrivning av intyg", ts, vardperiod, "testbeskrivning", "testbehandling", null, null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", "beskrivning av intyg", ts, vardperiod, "testbeskrivning", "testbehandling", "testgivare", null), 0),
                Arguments.of(new Forhandstillstand(uuid, kbhid, 0, "Forhandstillstand", person, period, "testtyp", "teststatus", "Försäkringskassan", "beskrivning av intyg", ts, vardperiod, "testbeskrivning", "testbehandling", "testgivare", "testlakare"), 0)
        );
    }
}
