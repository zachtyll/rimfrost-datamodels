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
import se.fk.mimer.producermodels.FysiskPerson;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

public class FysiskPersonTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag("FysiskPErsonValidationTest")
    @MethodSource("provideFysiskPersonTestData")
    void beslutIsValid( FysiskPerson beslut, int expectedResult) {
        Set<ConstraintViolation<FysiskPerson>> violations = validator.validate( beslut );
        assertEquals( violations.size(), expectedResult );
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

        return Stream.of(
                Arguments.of(new FysiskPerson(), 1),
                Arguments.of(new FysiskPerson(uuid, null, 0, null), 0),
                Arguments.of(new FysiskPerson(uuid, null, 0, "PERSONNUMMER"), 0),
                Arguments.of(new FysiskPerson(uuid, "TEST", 0, null), 0),
                Arguments.of(new FysiskPerson(uuid, "TEST", 0, "PERSONNUMMER"), 0)
        );
    }
}
