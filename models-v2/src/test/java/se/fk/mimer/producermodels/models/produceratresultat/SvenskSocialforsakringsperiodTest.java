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
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsstatus;
import se.fk.mimer.producermodels.v2.model.lagrum.Lagrum;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.svensksocialforsakringsperiod.Socialforsakringsgrund;
import se.fk.mimer.producermodels.v2.model.produceratresultat.svensksocialforsakringsperiod.SvenskSocialforsakringsperiod;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class SvenskSocialforsakringsperiodTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "SvenskSocialforsakringsperiodTest" )
    @MethodSource( "provideSvenskSocialforsakringsperiodTestData")
    void SvenskSocialforsakringsperiodIsValid( SvenskSocialforsakringsperiod svenskSocialforsakringsperiod, int expectedResult )
    {
        Set<ConstraintViolation<SvenskSocialforsakringsperiod>> violations = validator.validate( svenskSocialforsakringsperiod );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "SvenskSocialforsakringsperiodSerializationTest" )
    void SvenskSocialforsakringsperiodIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/svensksocialforsakringsperiod.json" );
        DataObject dataObject = mapper.readValue( data, SvenskSocialforsakringsperiod.class );
        assertInstanceOf( SvenskSocialforsakringsperiod.class, dataObject );
    }

    private static Stream<Arguments> provideSvenskSocialforsakringsperiodTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period period = TestObjectUtil.getPeriod();
        Lagrum lagrum = TestObjectUtil.getLagrum();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new SvenskSocialforsakringsperiod(), 4),
                Arguments.of(new SvenskSocialforsakringsperiod(null, null, 0, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, null, 0, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", null, null, null, null, null, null, null), 1),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", person, null, null, null, null, null, null), 0),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", person, period, null, null, null, null, null), 0),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", person, period, "testtyp", null, null, null, null), 0),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", person, period, "testtyp", "teststatus", null, null, null), 0),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", person, period, "testtyp", "teststatus", Socialforsakringsgrund.ARBETE, null, null), 0),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", person, period, "testtyp", "teststatus", Socialforsakringsgrund.ARBETE, lagrum, null), 0),
                Arguments.of(new SvenskSocialforsakringsperiod(uuid, kbhid, 0, "SvenskSocialforsakringsperiod", person, period, "testtyp", "teststatus", Socialforsakringsgrund.ARBETE, lagrum, Kundbehovsstatus.FASTSTALLTUNDERUTREDNING), 0)
        );
    }
}
