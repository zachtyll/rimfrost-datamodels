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
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.Kontouppgift;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.produceratresultat.Utforare;
import se.fk.mimer.producermodels.v2.model.person.Person;

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
    void UtforareIsValid( Utforare utforareImpl, int expectedResult) {
        Set<ConstraintViolation<Utforare>> violations = validator.validate(utforareImpl);
        System.out.println(violations.size());
        assertEquals( expectedResult, violations.size() );
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
        UUID kbhid = Generators.timeBasedEpochRandomGenerator().generate();
        UUID k1id = Generators.timeBasedEpochRandomGenerator().generate();
        UUID k2id = Generators.timeBasedEpochRandomGenerator().generate();
        Kontouppgift validKontoUtforare = new Kontouppgift(k1id, 1, "typ789", "nr0123", Boolean.TRUE);
        Kontouppgift validKontoBetaltjanst = new Kontouppgift( k2id, 1, "typ789", "nr0123", Boolean.TRUE);
        Person person = TestObjectUtil.getFysiskPerson();
        Period period = TestObjectUtil.getPeriod();

        return Stream.of(
                Arguments.of(new Utforare(), 4),
                Arguments.of(new Utforare(null, null, 0, null, null, null, null, null, null, null), 4),
                Arguments.of(new Utforare(uuid, null, 0, null, null, null, null, null, null, null), 3),
                Arguments.of(new Utforare(uuid, kbhid, 0, null, null, null, null, null, null,null), 2),
                Arguments.of(new Utforare(uuid, kbhid, 0, "Utforare", null, null, null, null, null,null), 1),
                Arguments.of(new Utforare(uuid, kbhid, 0, "Utforare", person, null, null, null, null,null), 0),
                Arguments.of(new Utforare(uuid, kbhid, 0, "Utforare", person, period, null, null, null,null), 0),
                Arguments.of(new Utforare(uuid, kbhid, 0, "Utforare", person, period, "Testtyp", "Teststatus", null, null), 0),
                Arguments.of(new Utforare(uuid, kbhid, 0, "Utforare", person, period, "Testtyp", "Teststatus", List.of(validKontoUtforare), null), 0),
                Arguments.of(new Utforare(uuid, kbhid, 0, "Utforare", person, period, "Testtyp", "Teststatus", List.of(validKontoUtforare), List.of(validKontoBetaltjanst)), 0)

        );
    }
}