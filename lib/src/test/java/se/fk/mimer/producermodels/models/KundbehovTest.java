package se.fk.mimer.producermodels.models;

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
import se.fk.mimer.producermodels.DataObject;
import se.fk.mimer.producermodels.Kundbehov;
import se.fk.mimer.producermodels.Period;
import se.fk.mimer.producermodels.RollIKundbehov;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class KundbehovTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest()
    @Tag( "KundbehovValidationTest" )
    @MethodSource( "provideKundbehovTestData" )
    void kundbehovIsValid( Kundbehov kundbehov, int expectedResult )
    {
        Set<ConstraintViolation<Kundbehov>> violations = validator.validate( kundbehov );
        assertEquals( violations.size(), expectedResult );
    }

    @Test
    @Tag( "KundbehovSerializationTest" )
    void KundbehovIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/kundbehov.json" );
        DataObject dataObject = mapper.readValue( data, Kundbehov.class );
        assertInstanceOf( Kundbehov.class, dataObject );
    }

    private static Stream<Arguments> provideKundbehovTestData()
    {
        ZonedDateTime date = ZonedDateTime.of(2024, 1, 1, 10, 0, 0, 0, ZoneId.systemDefault());
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        Period period = new Period( date, date.plusDays( 5 ) );
        RollIKundbehov rollIKundbehov = new RollIKundbehov("kundid" , "yrkande", true );
        return Stream.of(
                Arguments.of( new Kundbehov(), 7 ),
                Arguments.of( new Kundbehov( uuid, null, null, null,null, null, null, null, 0 ), 6 ),
                Arguments.of( new Kundbehov( uuid, "The", null, null, null, null, null, null, 0 ), 5 ),
                Arguments.of( new Kundbehov( uuid, "The", "Death", null, null, null, null, null, 0 ), 4 ),
                Arguments.of( new Kundbehov( uuid, "The", "Death", "Laser", null, null, null, null, 0 ), 4 ),
                Arguments.of( new Kundbehov( uuid, "The", "Death", "Laser", date, null, null, null, 0 ), 3 ),
                Arguments.of( new Kundbehov( uuid, "The", "Death", "Laser", date, period, null, null, 0 ), 2 ),
                Arguments.of( new Kundbehov( uuid, "The", "Death", "Laser", date, period, "avserErbjudande", null, 0 ), 1 ),
                Arguments.of( new Kundbehov( uuid, "The", "Death", "Laser", date, period, "avserErbjudande", List.of( rollIKundbehov ), 0 ), 0 ),
                Arguments.of( new Kundbehov( uuid, "The", "Death", "Laser", date, period, "Erbjudande", List.of( rollIKundbehov ), 0 ), 0 ) );
    }
}
