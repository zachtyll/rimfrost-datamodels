package se.fk.mimer.producermodels.models.produceratresultat.ersattning;

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
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.Ersattning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstypKategori;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.EBerakningsgrund;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.EBerakningsgrundRegel;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
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

class ErsattningTest
{
    Validator validator = inject( Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "ErsattningTest" )
    @MethodSource("provideErsattningTestData")
    void ersattningIsVaalid( Ersattning ersattning, int expectedResult )
    {
        Set<ConstraintViolation<Ersattning>> violations = validator.validate( ersattning );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "ErsattningSerializationTest")
    void ErsattningIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/ersattning.json" );
        DataObject dataObject = mapper.readValue( data, Ersattning.class );
        assertInstanceOf( Ersattning.class, dataObject );
    }

    private static Stream<Arguments> provideErsattningTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID kbhid = TestObjectUtil.getKundbehov().getId();
        Period giltighetsperiod = TestObjectUtil.getPeriod();
        Ersattning[] samordnasMed = TestObjectUtil.getSamordnasMed();
        ErsattningstypEnligtLagrum etyp = TestObjectUtil.getErsattningstypEnligtLagrum();
        OmfattningBaseratPaErsattningstypEnligtLagrum omfattning = TestObjectUtil.getOmfattningBaseratPaErsattningstypEnligtLagrum();
        Person person = TestObjectUtil.getFysiskPerson();

        return Stream.of(
                Arguments.of(new Ersattning(), 5),
                Arguments.of(new Ersattning(null, null, 0, null, null, null, null, null, 0.0, null, null, null, null, null, null, null, null, null), 5),
                Arguments.of(new Ersattning(uuid, null, 0, null, null, null, null, null, 0.0, null, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new Ersattning(uuid, kbhid, 0, null ,null, null, null, null, 0.0, null, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning",null, null, null, null, 0.0, null, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, null, null, null, 0.0, null, null, null, null, null, null, null, null, null), 1),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, null, null, null, 0.0, null, null, null, null, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, null, null, 0.0, null, null, null, null, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", null, 0.0, null, null, null, null, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, null, null, null, null, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, null, null, null, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, null, null, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, EBeloppstypKategori.AS, null, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, EBeloppstypKategori.AS, EBeloppstyp.SANKTIONSTAK, null, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, EBeloppstypKategori.AS, EBeloppstyp.SANKTIONSTAK, omfattning, null, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, EBeloppstypKategori.AS, EBeloppstyp.SANKTIONSTAK, omfattning, etyp, null, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, EBeloppstypKategori.AS, EBeloppstyp.SANKTIONSTAK, omfattning, etyp, Periodisering.MANAD, samordnasMed, null), 0),
                Arguments.of(new Ersattning(uuid, kbhid, 0, "Ersattning", person, giltighetsperiod, "test", "teststatus", 0.0, EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, EBeloppstypKategori.AS, EBeloppstyp.SANKTIONSTAK, omfattning, etyp, Periodisering.MANAD, samordnasMed, "testanledning"), 0)
        );
    }
}
