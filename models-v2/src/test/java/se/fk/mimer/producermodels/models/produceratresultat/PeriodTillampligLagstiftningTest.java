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
import se.fk.mimer.producermodels.v2.model.Land;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsstatus;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning.EArtikel;
import se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning.EForordning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning.PeriodTillampligLagstiftning;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class PeriodTillampligLagstiftningTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "PeriodTillampligLagstiftningTest" )
    @MethodSource( "providePeriodTillampligLagstiftningTestData")
    void PeriodTillampligLagstiftningIsValid( PeriodTillampligLagstiftning ptl, int expectedResult )
    {
        Set<ConstraintViolation<PeriodTillampligLagstiftning>> violations = validator.validate( ptl );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "PeriodTillampligLagstiftningSerializationTest" )
    void PeriodTillampligLagstiftningIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/periodtillampliglagstiftning.json" );
        DataObject dataObject = mapper.readValue( data, PeriodTillampligLagstiftning.class );
        assertInstanceOf( PeriodTillampligLagstiftning.class, dataObject );
    }

    private static Stream<Arguments> providePeriodTillampligLagstiftningTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new PeriodTillampligLagstiftning(), 4),
                Arguments.of(new PeriodTillampligLagstiftning(null, null, 0, null, null, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, null, 0, null, null, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, null, null, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", null, null, null, null, null, null, null, null, null), 1),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, null, null, null, null, null, null, null, null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, null, null, null, null, null, null, null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", null, null, null, null, null, null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", null, null, null, null, null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", EForordning.EEG140871, null, null, null, null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", EForordning.EEG140871, EArtikel.ARTIKEL_13_2a, null, null, null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", EForordning.EEG140871, EArtikel.ARTIKEL_13_2a, Land.SVERIGE, null, null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", EForordning.EEG140871, EArtikel.ARTIKEL_13_2a, Land.SVERIGE, "Sverige", null), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", EForordning.EEG140871, EArtikel.ARTIKEL_13_2a, Land.SVERIGE, "Sverige", Kundbehovsstatus.UNDERUTREDNING), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", EForordning.EEG8592003, EArtikel.ARTIKEL13_2a, Land.SVERIGE, "Sverige", Kundbehovsstatus.UNDERUTREDNING), 0),
                Arguments.of(new PeriodTillampligLagstiftning(uuid, kbhid, 0, "PeriodTillampligLagstiftning", person, period, "testtyp", "teststatus", EForordning.EEG8832004, EArtikel.ARTIKEL11_3A, Land.SVERIGE, "Sverige", Kundbehovsstatus.UNDERUTREDNING), 0)
        );
    }
}
