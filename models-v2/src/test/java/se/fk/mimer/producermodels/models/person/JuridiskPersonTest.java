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
import se.fk.mimer.producermodels.v2.model.person.JuridiskPerson;
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

class JuridiskPersonTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "JuridiskPersonTest" )
    @MethodSource( "provideJuridiskPersonTestData" )
    void juridiskPersonIsValid( JuridiskPerson jp, int expectedResult )
    {
        Set<ConstraintViolation<JuridiskPerson>> violations = validator.validate( jp );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "JuridiskPersonSerializationTest" )
    void JuridiskPersonIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/juridiskperson.json" );
        DataObject dataObject = mapper.readValue( data, JuridiskPerson.class );
        assertInstanceOf( JuridiskPerson.class, dataObject );
    }

    private static Stream<Arguments> provideJuridiskPersonTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        Map<UUID, RollIKundbehov> roll = TestObjectUtil.getRollIKundbehovMap();

        return Stream.of(
                Arguments.of(new JuridiskPerson(), 3),
                Arguments.of(new JuridiskPerson(null, null, 0, null, null, null), 3),
                Arguments.of(new JuridiskPerson(uuid, null, 0, null, null, null), 2),
                Arguments.of(new JuridiskPerson(uuid, "1234567811234", 0, null, null, null), 2),
                Arguments.of(new JuridiskPerson(uuid, "1234567811234", 0, "JuridiskPerson", null, null), 1),
                Arguments.of(new JuridiskPerson(uuid, "1234567811234", 0, "JuridiskPerson", "11223344", null), 1),
                Arguments.of(new JuridiskPerson(uuid, "1234567811234", 0, "JuridiskPerson", "11223344", roll), 0)
        );
    }
}
