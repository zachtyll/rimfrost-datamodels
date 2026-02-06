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
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.IVIntyg;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.IVIntygstyp;
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

class IVIntygTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "IVIntygTest" )
    @MethodSource( "provideIVIntygTestData")
    void IVIntygIsValid( IVIntyg ivintyg, int expectedResult )
    {
        Set<ConstraintViolation<IVIntyg>> violations = validator.validate( ivintyg );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "IVIntygSerializationTest" )
    void IVIntygIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/ivintyg.json" );
        DataObject dataObject = mapper.readValue( data, IVIntyg.class );
        assertInstanceOf( IVIntyg.class, dataObject );
    }

    private static Stream<Arguments> provideIVIntygTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();

        Period period = TestObjectUtil.getPeriod();
        ZonedDateTime ts = TestObjectUtil.getDate();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new IVIntyg(), 4),
                Arguments.of(new IVIntyg(null, null, 0, null, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new IVIntyg(uuid, null, 0, null, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, null, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", null, null, null, null, null, null, null, null), 1),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, null, null, null, null, null, null, null), 0),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, period, null, null, null, null, null, null), 0),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, period, "testtyp", null, null, null, null, null), 0),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, period, "testtyp", "teststatus", null, null, null, null), 0),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, period, "testtyp", "teststatus", "Skatteverket", null, null, null), 0),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, period, "testtyp", "teststatus", "Skatteverket", "testbeskrivning", null, null), 0),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, period, "testtyp", "teststatus", "Skatteverket", "testbeskrivning", ts, null), 0),
                Arguments.of(new IVIntyg(uuid, kbhid, 0, "IVIntyg", person, period, "testtyp", "teststatus", "Skatteverket", "testbeskrivning", ts, IVIntygstyp.DA1), 0)
        );
    }
}
