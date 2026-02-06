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
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.IntygOmTillampligLagstiftning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.Intygstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.UtlandsktForetag;
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

class IntygOmTillampligLagstiftningTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "IntygOmTillampligLagstiftningTest" )
    @MethodSource( "provideIntygOmTillampligLagstiftningTestData")
    void IntygOmTillampligLagstifningIsValid( IntygOmTillampligLagstiftning intyg, int expectedResult )
    {
        Set<ConstraintViolation<IntygOmTillampligLagstiftning>> violations = validator.validate( intyg );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "IntygOmTillampligLagstifningSerializationTest" )
    void IntygOmTillampligLagstifningIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/intygomtillampliglagstiftning.json" );
        DataObject dataObject = mapper.readValue( data, IntygOmTillampligLagstiftning.class );
        assertInstanceOf( IntygOmTillampligLagstiftning.class, dataObject );
    }

    private static Stream<Arguments> provideIntygOmTillampligLagstiftningTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        ZonedDateTime ts = TestObjectUtil.getDate();
        UtlandsktForetag uf = TestObjectUtil.getUtlandsktForetag();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new IntygOmTillampligLagstiftning(), 4),
                Arguments.of(new IntygOmTillampligLagstiftning(null, null, 0, null, null, null, null, null, null, null, null, null, null, true, false, true, false), 4),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, null, 0, null, null, null, null, null, null, null, null, null, null, true, false, true, false), 3),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, null, null, null, null, null, null, null, null, null, null, true, false, true, false), 2),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", null, null, null, null, null, null, null, null, null, true, false, true, false), 1),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, null, null, null, null, null, null, null, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, null, null, null, null, null, null, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, "testtyp", null, null, null, null, null, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, "testtyp", "teststatus", null, null, null, null, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, "testtyp", "teststatus", "Skatteverket", null, null, null, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, "testtyp", "teststatus", "Skatteverket", "testbeskrivning", null, null, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, "testtyp", "teststatus", "Skatteverket", "testbeskrivning", ts, null, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, "testtyp", "teststatus", "Skatteverket", "testbeskrivning", ts, uf, null, true, false, true, false), 0),
                Arguments.of(new IntygOmTillampligLagstiftning(uuid, kbhid, 0, "IntygOmTillampligLagstiftning", person, period, "testtyp", "teststatus", "Skatteverket", "testbeskrivning", ts, uf, Intygstyp.E101, true, false, true, false), 0)
        );
    }
}
