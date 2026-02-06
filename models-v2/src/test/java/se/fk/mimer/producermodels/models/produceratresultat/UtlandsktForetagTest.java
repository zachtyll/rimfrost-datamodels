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
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.UtlandsktForetag;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class UtlandsktForetagTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "UtlandsktForetagTest" )
    @MethodSource( "provideUtlandsktForetagTestData")
    void UtlandsktForetagIsValid( UtlandsktForetag utlandsktForetag, int expectedResult )
    {
        Set<ConstraintViolation<UtlandsktForetag>> violations = validator.validate( utlandsktForetag );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "UtlandsktForetagSerializationTest" )
    void UtlandsktForetagIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/utlandsktforetag.json" );
        DataObject dataObject = mapper.readValue( data, UtlandsktForetag.class );
        assertInstanceOf( UtlandsktForetag.class, dataObject );
    }

    private static Stream<Arguments> provideUtlandsktForetagTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();

        return Stream.of(
                Arguments.of(new UtlandsktForetag(), 4),
                Arguments.of(new UtlandsktForetag(null, 0, null, null, null), 4),
                Arguments.of(new UtlandsktForetag(uuid, 0, null, null, null), 3),
                Arguments.of(new UtlandsktForetag(uuid, 0, "UtlandsktForetag", null, null), 2),
                Arguments.of(new UtlandsktForetag(uuid, 0, "UtlandsktForetag", "Testföretaget OY", null), 1),
                Arguments.of(new UtlandsktForetag(uuid, 0, "UtlandsktForetag", "Testföretaget OY", "11223311"), 0)
        );
    }
}
