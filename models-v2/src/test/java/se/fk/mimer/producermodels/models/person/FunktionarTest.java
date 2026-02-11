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
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;
import se.fk.mimer.producermodels.v2.model.person.EnskildNaringsidkare;
import se.fk.mimer.producermodels.v2.model.person.FysiskPerson;
import se.fk.mimer.producermodels.v2.model.person.foretradare.Foretradartyp;
import se.fk.mimer.producermodels.v2.model.person.foretradare.Funktionar;
import se.fk.mimer.producermodels.v2.model.person.foretradare.Funktionarstyp;
import se.fk.mimer.producermodels.v2.model.produkt.RollIProdukt;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class FunktionarTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "FunktionarTest" )
    @MethodSource( "provideFunktionarTestData")
    void funktionarIsValid( Funktionar funktionar, int expectedResult )
    {
        Set<ConstraintViolation<Funktionar>> violations = validator.validate( funktionar );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "FunktionarSerializationTest" )
    void FunktionarIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/funktionar.json" );
        DataObject dataObject = mapper.readValue( data, Funktionar.class );
        assertInstanceOf( Funktionar.class, dataObject );
    }

    private static Stream<Arguments> provideFunktionarTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID funktionarid = TestObjectUtil.getFunktionar().getFunktionarId();
        Period period = TestObjectUtil.getPeriod();
        Map<UUID, RollIKundbehov> roll = TestObjectUtil.getRollIKundbehovMap();
        RollIProdukt rollip = TestObjectUtil.getRollIProdukt();
        EnskildNaringsidkare ni = TestObjectUtil.getNaringsidkare();
        FysiskPerson person = TestObjectUtil.getForetraderPerson();

        return Stream.of(
                Arguments.of(new Funktionar(), 7),
                Arguments.of(new Funktionar(null, null, 0, null, null, null, null, null, null, null, null, null, null, null), 7),
                Arguments.of(new Funktionar(uuid, null, 0, null, null, null, null, null, null, null, null, null, null, null), 6),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, null,null, null, null, null, null, null, null, null, null, null), 4),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar" ,null, null, null, null, null, null, null, null, null, null), 3),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, null, null, null, null, null, null, null, null, null), 2),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", null, null, null, null, null, null, null, null), 2),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, null, null, null, null, null, null, null), 2),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, null, null, null, null, null, null), 2),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, "1234567811234", null, null, null, null, null), 1),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, "1234567811234", funktionarid, null, null, null, null), 0),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, "1234567811234", funktionarid, Foretradartyp.FULLMAKT, null, null, null), 0),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, "1234567811234", funktionarid, Foretradartyp.GODMAN, period, null, null), 0),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, "1234567811234", funktionarid, Foretradartyp.OMBUD, period, person, null), 0),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, "1234567811234", funktionarid, Foretradartyp.SAMTYCKE, period, person, Funktionarstyp.FIRMATECKNARE), 0),
                Arguments.of(new Funktionar(uuid, "1234567811234", 0, "Funktionar", roll, "12345678-1234", rollip, ni, "1234567811234", funktionarid, Foretradartyp.STALLFORETRADARE, period, person, Funktionarstyp.VD), 0)
        );
    }
}
