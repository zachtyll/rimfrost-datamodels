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
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;
import se.fk.mimer.producermodels.v2.model.person.EnskildNaringsidkare;
import se.fk.mimer.producermodels.v2.model.person.FysiskPerson;
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

class FysiskPersonTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag("FysiskPErsonValidationTest")
    @MethodSource("provideFysiskPersonTestData")
    void beslutIsValid( FysiskPerson beslut, int expectedResult) {
        Set<ConstraintViolation<FysiskPerson>> violations = validator.validate( beslut );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "FysiskPersonSerializationTest" )
    void FysiskPersonIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/fysiskperson.json" );
        DataObject dataObject = mapper.readValue( data, FysiskPerson.class );
        assertInstanceOf( FysiskPerson.class, dataObject );
    }

    private static Stream<Arguments> provideFysiskPersonTestData() {
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        RollIProdukt rollip = TestObjectUtil.getRollIProdukt();
        EnskildNaringsidkare ni = TestObjectUtil.getNaringsidkare();
        Map<UUID, RollIKundbehov> roll = TestObjectUtil.getRollIKundbehovMap();

        return Stream.of(
                Arguments.of(new FysiskPerson(), 4),
                Arguments.of(new FysiskPerson(null, null, 0, null, null, null, null, null), 4),
                Arguments.of(new FysiskPerson(uuid, null, 0, null, null, null, null, null), 3),
                Arguments.of(new FysiskPerson(uuid, null, 0, "FysiskPerson", null, null, null, null), 2),
                Arguments.of(new FysiskPerson(uuid, "1234567811234", 0, "FysiskPerson", null, null, null, null), 1),
                Arguments.of(new FysiskPerson(uuid, "1234567811234", 0, "FysiskPerson", "12345678-1234", null, null, null), 1),
                Arguments.of(new FysiskPerson(uuid, "1234567811234", 0, "FysiskPerson", "12345678-1234", rollip, null, null), 1),
                Arguments.of(new FysiskPerson(uuid, "1234567811234", 0, "FysiskPerson", "12345678-1234", rollip, ni, null), 1),
                Arguments.of(new FysiskPerson(uuid, "1234567811234", 0, "FysiskPerson", "12345678-1234", rollip, ni, roll), 0)
        );
    }
}
