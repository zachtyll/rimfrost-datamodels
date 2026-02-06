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
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstypKategori;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.krav.Krav;
import se.fk.mimer.producermodels.v2.model.produceratresultat.krav.Kravtyp;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.Periodisering;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class KravTest
{
    Validator validator = inject( Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "KravTest" )
    @MethodSource( "provideKravTestData" )
    void kravIsValid( Krav krav, int expectedResult)
    {
        Set<ConstraintViolation<Krav>> violations = validator.validate( krav );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "KravSerializationTest" )
    void KravIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/krav.json" );
        DataObject dataObject = mapper.readValue( data, Krav.class );
        assertInstanceOf( Krav.class, dataObject );
    }

    private static Stream<Arguments> provideKravTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period giltighetsperiod = TestObjectUtil.getPeriod();
        OmfattningBaseratPaErsattningstypEnligtLagrum omfattning = TestObjectUtil.getOmfattningBaseratPaErsattningstypEnligtLagrum();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new Krav(), 4),
                Arguments.of(new Krav(null, null, 0, null, null, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new Krav(uuid, null, 0, null, null, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new Krav(uuid, kbhid, 0, null, null, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", null, null, null, null, null, null, null, null, null), 1),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, null, null, null, null, null, null, null, null), 0),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, giltighetsperiod, null, null, null, null, null, null, null), 0),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, giltighetsperiod, "test", null, null, null, null, null, null), 0),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, giltighetsperiod, "test", "teststatus", EBeloppstypKategori.AS, null, null, null, null), 0),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, giltighetsperiod, "test", "teststatus", EBeloppstypKategori.AS, EBeloppstyp.AKASSA, null, null, null), 0),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, giltighetsperiod, "test", "teststatus", EBeloppstypKategori.AS, EBeloppstyp.AKASSA, Kravtyp.BETALNINGSBELOPP_FOR_UNDERHALLSSTOD, null, null), 0),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, giltighetsperiod, "test", "teststatus", EBeloppstypKategori.AS, EBeloppstyp.AKASSA, Kravtyp.BETALNINGSBELOPP_FOR_UNDERHALLSSTOD, Periodisering.DAG, null), 0),
                Arguments.of(new Krav(uuid, kbhid, 0, "Krav", person, giltighetsperiod, "test", "teststatus", EBeloppstypKategori.AS, EBeloppstyp.AKASSA, Kravtyp.BETALNINGSBELOPP_FOR_UNDERHALLSSTOD, Periodisering.DAG, omfattning ), 0)
        );
    }
}
