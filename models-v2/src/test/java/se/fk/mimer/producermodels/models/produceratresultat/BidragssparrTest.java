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
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.AnledningIngenBidragssparr;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.BedomdMedvetenhet;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.Bidragssparr;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.BidragssparrStatus;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.GrundForBeslut;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.GrundForUtredning;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

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
        assertEquals( expectedResult, violations.size() );
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
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        Period period = TestObjectUtil.getPeriod();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new Bidragssparr(), 4),
                Arguments.of(new Bidragssparr(null, null, 0, null, null, null, null, null, null, null, false, null, null, null), 4),
                Arguments.of(new Bidragssparr(uuid, null, 0, null, null, null, null, null, null, null, false, null, null, null), 3),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, null, null, null, null, null, null, null, false, null, null, null), 2),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", null, null, null, null, null, null, false, null, null, null), 1),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, null, null, null, null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, null, null, null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, "", null, null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, "", "", null, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, null, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, null, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, BidragssparrStatus.BORTTAGEN, null, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, BidragssparrStatus.BORTTAGEN, GrundForBeslut.VARKEN_ORIKTIGA_UPPGIFTER_ELLER_UNDERLATIT_SIG_ANMALNINGSSKYLDIGHET, null), 0),
                Arguments.of(new Bidragssparr(uuid, uuid, 0, "Bidragssparr", person, period, "", "", AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST, false, BidragssparrStatus.BORTTAGEN, GrundForBeslut.VARKEN_ORIKTIGA_UPPGIFTER_ELLER_UNDERLATIT_SIG_ANMALNINGSSKYLDIGHET, GrundForUtredning.BROTTSMISSTANKE), 0)
        );
    }
}
