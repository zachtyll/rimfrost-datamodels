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
import se.fk.mimer.producermodels.v2.model.kundbehov.Avsikt;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehov;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsstatus;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;
import se.fk.mimer.producermodels.v2.model.produkt.Erbjudande;
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
        assertEquals( expectedResult, violations.size() );
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
        ZonedDateTime date = TestObjectUtil.getDate();
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        Period period = new Period( date, date.plusDays( 5 ) );
        RollIKundbehov rollIKundbehov = TestObjectUtil.getRollIKundbehov();
        Erbjudande erbjudande = TestObjectUtil.getErbjudande();
        return Stream.of(
                Arguments.of( new Kundbehov(), 1),
                Arguments.of( new Kundbehov( uuid, 0, null, null, null,null, null, null, null ), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, null, null, null, null, null, null), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, null, null, null, null, null), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning", null, null, null, null), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning", date, null, null, null), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning", date, period, null, null), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning", date, period, erbjudande, null), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning", date, period, erbjudande, List.of( rollIKundbehov )), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning", date, period, erbjudande, List.of( rollIKundbehov )), 0),
                Arguments.of( new Kundbehov( uuid, 0, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning", date, period, erbjudande, List.of( rollIKundbehov )), 0 ) );
    }
}
