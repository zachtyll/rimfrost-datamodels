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
import se.fk.mimer.producermodels.v2.model.produceratresultat.RattenTillPeriod;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class RattenTillPeriodTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "RattenTillPeriodTest" )
    @MethodSource( "provideRattenTillPeriodTestData")
    void RattenTillPeriodIsValid( RattenTillPeriod rattenTillPeriod, int expectedResult )
    {
        Set<ConstraintViolation<RattenTillPeriod>> violations = validator.validate( rattenTillPeriod );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "RattenTillPeriodSerializationTest" )
    void RattenTillPeriodIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/rattentillperiod.json" );
        DataObject dataObject = mapper.readValue( data, RattenTillPeriod.class );
        assertInstanceOf( RattenTillPeriod.class, dataObject );
    }

    private static Stream<Arguments> provideRattenTillPeriodTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new RattenTillPeriod(), 4),
                Arguments.of(new RattenTillPeriod(null, null, 0, null, null, null, null, null, null, null), 4),
                Arguments.of(new RattenTillPeriod(uuid, null, 0, null, null, null, null, null, null, null), 3),
                Arguments.of(new RattenTillPeriod(uuid, kbhid, 0, null, null, null, null, null, null, null), 2),
                Arguments.of(new RattenTillPeriod(uuid, kbhid, 0, "RattenTillPeriod", person, null,  null, null, null, null), 0),
                Arguments.of(new RattenTillPeriod(uuid, kbhid, 0, "RattenTillPeriod", person, period, null, null, null, null), 0),
                Arguments.of(new RattenTillPeriod(uuid, kbhid, 0, "RattenTillPeriod", person, period, "testtyp", null, null, null), 0),
                Arguments.of(new RattenTillPeriod(uuid, kbhid, 0, "RattenTillPeriod", person, period, "testtyp", "teststatus", null, null), 0),
                Arguments.of(new RattenTillPeriod(uuid, kbhid, 0, "RattenTillPeriod", person, period, "testtyp", "teststatus", "testomfattning", null), 0),
                Arguments.of(new RattenTillPeriod(uuid, kbhid, 0, "RattenTillPeriod", person, period, "testtyp", "teststatus", "testomfattning", "testersattning"), 0)
        );
    }
}
