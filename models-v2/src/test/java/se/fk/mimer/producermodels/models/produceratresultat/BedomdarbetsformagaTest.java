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
import se.fk.mimer.producermodels.v2.model.produceratresultat.Bedomdarbetsformaga;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class BedomdarbetsformagaTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "BedomdarbetsformagaTest" )
    @MethodSource( "provideBedomdarbetsformagaTestData")
    void BedomdArbetsformagaIsValid( Bedomdarbetsformaga bedomdarbetsformaga, int expectedResult )
    {
        Set<ConstraintViolation<Bedomdarbetsformaga>> violations = validator.validate( bedomdarbetsformaga );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "BedomdarbetsformagaSerializationTest" )
    void BedomdarbetsformagaIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/bedomdarbetsformaga.json" );
        DataObject dataObject = mapper.readValue( data, Bedomdarbetsformaga.class );
        assertInstanceOf( Bedomdarbetsformaga.class, dataObject );
    }

    private static Stream<Arguments> provideBedomdarbetsformagaTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new Bedomdarbetsformaga(), 4),
                Arguments.of(new Bedomdarbetsformaga(null, null, 0, null, null, null, null, null, null), 4),
                Arguments.of(new Bedomdarbetsformaga(uuid, null, 0, null, null, null, null, null, null), 3),
                Arguments.of(new Bedomdarbetsformaga(uuid, kbhid, 0, null, null, null, null, null, null), 2),
                Arguments.of(new Bedomdarbetsformaga(uuid, kbhid, 0, "BedomdArbetsformaga", null, null, null, null, null), 1),
                Arguments.of(new Bedomdarbetsformaga(uuid, kbhid, 0, "BedomdArbetsformaga", person, null, null, null, null), 0),
                Arguments.of(new Bedomdarbetsformaga(uuid, kbhid, 0, "BedomdArbetsformaga", person, period, null, null, null), 0),
                Arguments.of(new Bedomdarbetsformaga(uuid, kbhid, 0, "BedomdArbetsformaga", person, period, "testtyp", null, null), 0),
                Arguments.of(new Bedomdarbetsformaga(uuid, kbhid, 0, "BedomdArbetsformaga", person, period, "testtyp", "teststatus", null), 0),
                Arguments.of(new Bedomdarbetsformaga(uuid, kbhid, 0, "BedomdArbetsformaga", person, period, "testtyp", "teststatus", "halvtid"), 0)
        );
    }
}
