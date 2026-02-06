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
import se.fk.mimer.producermodels.v2.model.produceratresultat.Periodisering;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdkostnad.BedomdKostnad;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdkostnad.Kostnadstyp;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class BedomdKostnadTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "BedomdKostnadTest" )
    @MethodSource( "provideBedomdKostnadTestData")
    void BedomdKostnadIsValid( BedomdKostnad bedomdKostnad, int expectedResult )
    {
        Set<ConstraintViolation<BedomdKostnad>> violations = validator.validate( bedomdKostnad );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "BedomdKostnadSerializationTest" )
    void BedomdKostnadIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/bedomdkostnad.json" );
        DataObject dataObject = mapper.readValue( data, BedomdKostnad.class );
        assertInstanceOf( BedomdKostnad.class, dataObject );
    }

    private static Stream<Arguments> provideBedomdKostnadTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new BedomdKostnad(), 4),
                Arguments.of(new BedomdKostnad(null, null, 0, null, null, null, null, null, 0.0, null, null), 4),
                Arguments.of(new BedomdKostnad(uuid, null, 0, null, null, null, null, null, 0.0, null, null), 3),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, null, null, null, null, null, 0.0, null, null), 2),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, "BedomdKostnad", null, null, null, null, 0.0, null, null), 1),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, "BedomdKostnad", person, null, null, null, 0.0, null, null), 0),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, "BedomdKostnad", person, period, null, null, 0.0, null, null), 0),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, "BedomdKostnad", person, period, "testtyp", null, 0.0, null, null), 0),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, "BedomdKostnad", person, period, "testtyp", "teststatus", 0.0, null, null), 0),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, "BedomdKostnad", person, period, "testtyp", "teststatus", 0.0, Kostnadstyp.BOENDEKOSTNAD, null), 0),
                Arguments.of(new BedomdKostnad(uuid, kbhid, 0, "BedomdKostnad", person, period, "testtyp", "teststatus", 0.0, Kostnadstyp.BOENDEKOSTNAD, Periodisering.AR), 0)
        );
    }
}
