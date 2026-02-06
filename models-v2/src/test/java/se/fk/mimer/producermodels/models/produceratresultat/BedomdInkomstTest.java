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
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.BedomdInkomst;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.Beloppstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.EInkomsttyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.EInkomsttypKategori;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class BedomdInkomstTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "BedomdInkomstTest" )
    @MethodSource( "provideBedomdInkomstTestData")
    void bedomdInkomstIsValid( BedomdInkomst bedomdInkomst, int expectedResult )
    {
        Set<ConstraintViolation<BedomdInkomst>> violations = validator.validate( bedomdInkomst );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "BedomdInkomstSerializationTest" )
    void BedomdInkomstIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/bedomdinkomst.json" );
        DataObject dataObject = mapper.readValue( data, BedomdInkomst.class );
        assertInstanceOf( BedomdInkomst.class, dataObject );
    }

    private static Stream<Arguments> provideBedomdInkomstTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new BedomdInkomst(), 4),
                Arguments.of(new BedomdInkomst(uuid, null, 0, null, null, null, null, null, 0.0, null, null, null, null), 3),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, null, null, null, null, null, 0.0, null, null, null, null), 2),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", null, null, null, null, 0.0, null, null, null, null), 1),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, null, null, null, 0.0, null, null, null, null), 0),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, period, null, null, 0.0, null, null, null, null), 0),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, period, "testtyp", null, 0.0, null, null, null, null), 0),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, period, "testtyp", "teststatus", 0.0, null, null, null, null), 0),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, period, "testtyp", "teststatus", 0.0, EInkomsttyp.SKYDDAD, null, null, null), 0),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, period, "testtyp", "teststatus", 0.0, EInkomsttyp.SKYDDAD, EInkomsttypKategori.SGI, null, null), 0),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, period, "testtyp", "teststatus", 0.0, EInkomsttyp.SKYDDAD, EInkomsttypKategori.SGI, Periodisering.VECKA, null), 0),
                Arguments.of(new BedomdInkomst(uuid, kbhid, 0, "BedomdInkomst", person, period, "testtyp", "teststatus", 0.0, EInkomsttyp.A, EInkomsttypKategori.BGI, Periodisering.ENGANGS, Beloppstyp.STUDIEBIDRAG), 0)
        );
    }
}
