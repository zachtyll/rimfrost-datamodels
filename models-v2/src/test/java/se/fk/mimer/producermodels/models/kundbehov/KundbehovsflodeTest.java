package se.fk.mimer.producermodels.models.kundbehov;

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
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsflode;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class KundbehovsflodeTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest()
    @Tag("KundbehovsflodeValidationTest")
    @MethodSource("provideKundbehovsflodeTestData")
    void kundbehovsflodeIsValid( Kundbehovsflode kundbehovsflode, int expectedResult) {
        Set<ConstraintViolation<Kundbehovsflode>> violations = validator.validate( kundbehovsflode );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "KundbehovsflodeSerializationTest" )
    void KundbehovsflodeIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/kundbehovsflode.json" );
        DataObject dataObject = mapper.readValue( data, Kundbehovsflode.class );
        assertInstanceOf( Kundbehovsflode.class, dataObject );
    }

    private static Stream<Arguments> provideKundbehovsflodeTestData() {
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        ZonedDateTime date = TestObjectUtil.getDate();
        return Stream.of(
                Arguments.of(new Kundbehovsflode(), 1),
                Arguments.of(new Kundbehovsflode(uuid, 0, null, null, null, null), 0),
                Arguments.of(new Kundbehovsflode(uuid, 0, null, null, null, null), 0),
                Arguments.of(new Kundbehovsflode(uuid, 0, null, null, null, null), 0),
                Arguments.of(new Kundbehovsflode(uuid, 0, date.plusDays( 5 ), null, null, null), 0),
                Arguments.of(new Kundbehovsflode(uuid, 0, date.plusDays( 5 ), date, null, null), 0),
                Arguments.of(new Kundbehovsflode(uuid, 0, date.plusDays( 5 ), date, List.of( uuid ), null), 0),
                Arguments.of(new Kundbehovsflode(uuid, 0, date.plusDays( 5 ), date, List.of( uuid ), "arendeId"), 0)
        );
    }
}
