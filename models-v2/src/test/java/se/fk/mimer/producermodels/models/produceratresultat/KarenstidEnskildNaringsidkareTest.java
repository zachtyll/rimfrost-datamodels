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
import se.fk.mimer.producermodels.v2.model.person.EnskildNaringsidkare;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.KarenstidEnskildNaringsidkare;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class KarenstidEnskildNaringsidkareTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "KarenstidEnskildNaringsidkareTest" )
    @MethodSource( "provideKarenstidEnskildNaringsidkareTestData")
    void KarenstidEnskildNaringsidkareIsValid( KarenstidEnskildNaringsidkare karenstidEnskildNaringsidkare, int expectedResult )
    {
        Set<ConstraintViolation<KarenstidEnskildNaringsidkare>> violations = validator.validate( karenstidEnskildNaringsidkare );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "KarenstidEnskildNaringsidkareSerializationTest" )
    void KarenstidEnskildNaringsidkareIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/karenstidenskildnaringsidkare.json" );
        DataObject dataObject = mapper.readValue( data, KarenstidEnskildNaringsidkare.class );
        assertInstanceOf( KarenstidEnskildNaringsidkare.class, dataObject );
    }

    private static Stream<Arguments> provideKarenstidEnskildNaringsidkareTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        EnskildNaringsidkare[] idkare = TestObjectUtil.getKarenstidEnskildNaringsidkare().getNaringsidkare();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new KarenstidEnskildNaringsidkare(), 5),
                Arguments.of(new KarenstidEnskildNaringsidkare(null, 0, null, null, null, null, null, null, 1, null), 5),
                Arguments.of(new KarenstidEnskildNaringsidkare(uuid, 0, null, null, null, null, null, null, 1, null), 4),
                Arguments.of(new KarenstidEnskildNaringsidkare(uuid, 0, "KarenstidEnskildNaringsidkare", null, null, null, null, null, 1, null), 3),
                Arguments.of(new KarenstidEnskildNaringsidkare(uuid, 0, "KarenstidEnskildNaringsidkare", kbhid, person, null, null, null, 1, null), 1),
                Arguments.of(new KarenstidEnskildNaringsidkare(uuid, 0, "KarenstidEnskildNaringsidkare", kbhid, person, period, null, null, 1, null), 1),
                Arguments.of(new KarenstidEnskildNaringsidkare(uuid, 0, "KarenstidEnskildNaringsidkare", kbhid, person, period, "testtyp", null, 1, null), 1),
                Arguments.of(new KarenstidEnskildNaringsidkare(uuid, 0, "KarenstidEnskildNaringsidkare", kbhid, person, period, "testtyp", "teststatus", 1, null), 1),
                Arguments.of(new KarenstidEnskildNaringsidkare(uuid, 0, "KarenstidEnskildNaringsidkare", kbhid, person, period, "testtyp", "teststatus", 1, idkare), 0)
        );
    }
}
