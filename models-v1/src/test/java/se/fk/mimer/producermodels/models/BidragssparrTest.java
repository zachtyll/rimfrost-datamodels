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
import se.fk.mimer.producermodels.Ersattning;
import se.fk.mimer.producermodels.Period;
import se.fk.mimer.producermodels.bidragssparr.AnledningIngenBidragssparr;
import se.fk.mimer.producermodels.bidragssparr.BedomdMedvetenhet;
import se.fk.mimer.producermodels.bidragssparr.Bidragssparr;
import se.fk.mimer.producermodels.bidragssparr.BidragssparrStatus;
import se.fk.mimer.producermodels.bidragssparr.GrundForBeslut;
import se.fk.mimer.producermodels.bidragssparr.GrundForUtredning;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class BidragssparrTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "BidragssparrTest" )
    @MethodSource( "provideBidragssparrTestData")
    void BidragssparrIsValid( Bidragssparr bidragssparr, int expectedResult )
    {
        Set<ConstraintViolation<Bidragssparr>> violations = validator.validate( bidragssparr );
        assertEquals( violations.size(), expectedResult );
    }

    @Test
    @Tag( "BidragssparrSerializationTest" )
    void BidragssparrIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/bidragssparr.json" );
        DataObject dataObject = mapper.readValue( data, Bidragssparr.class );
        assertInstanceOf( Bidragssparr.class, dataObject );
    }

    private static Stream<Arguments> provideBidragssparrTestData()
    {
        ZonedDateTime date = ZonedDateTime.of(2024, 1, 1, 10, 0, 0, 0, ZoneId.systemDefault());
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        BigDecimal amount = new BigDecimal("1000.00");
        double omfattning = 0.0;
        new Ersattning(uuid, uuid, 0, "", Period.builder().build(), "", "", amount, omfattning, "", "", "", "", "" );

        return Stream.of(
                Arguments.of(new Bidragssparr(), 3),
                Arguments.of(new Bidragssparr(null, null, 0, null, null, null, null, null, null, false, null, null, null), 3),
                Arguments.of(new Bidragssparr(uuid, null, 0, null, null, null, null, null, null, false, null, null, null), 2),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, null, null, null, null, null, null, false, null, null, null), 1),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", null, null, null, null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), null, null, null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), "", null, null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), "", "", null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, BidragssparrStatus.BORTTAGEN, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, BidragssparrStatus.BORTTAGEN, GrundForBeslut.VARKEN_ORIKTIGA_UPPGIFTER_ELLER_UNDERLATIT_SIG_ANMALNINGSSKYLDIGHET, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "1234567811234", new Period(date, date.plusDays( 5 )), "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, BidragssparrStatus.BORTTAGEN, GrundForBeslut.VARKEN_ORIKTIGA_UPPGIFTER_ELLER_UNDERLATIT_SIG_ANMALNINGSSKYLDIGHET, GrundForUtredning.BROTTSMISSTANKE), 0)
        );
    }
}
