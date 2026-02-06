package se.fk.mimer.producermodels.models.person;

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
import se.fk.mimer.producermodels.v2.model.person.EnskildNaringsidkare;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class EnskildNaringsidkareTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "EnskildNaringsidkareTest" )
    @MethodSource( "provideEnskildNaringsidkareTestData" )
    void enskildNaringsidkareIsValid( EnskildNaringsidkare en, int expectedResult )
    {
        Set<ConstraintViolation<EnskildNaringsidkare>> violations = validator.validate( en );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "EnskildNaringsidkareSerializationTest" )
    void EnskildNaringsidkareIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/enskildNaringsidkare.json" );
        DataObject dataObject = mapper.readValue( data, EnskildNaringsidkare.class );
        assertInstanceOf( EnskildNaringsidkare.class, dataObject );
    }

    private static Stream<Arguments> provideEnskildNaringsidkareTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();

        return Stream.of(
                Arguments.of(new EnskildNaringsidkare(), 1),
                Arguments.of(new EnskildNaringsidkare(null, 0), 1),
                Arguments.of(new EnskildNaringsidkare(uuid, 0), 0),
                Arguments.of(new EnskildNaringsidkare(uuid, 0), 0)
        );
    }
}
