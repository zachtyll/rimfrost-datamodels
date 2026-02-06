package se.fk.mimer.producermodels.models.produkt;

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
import se.fk.mimer.producermodels.v2.model.produkt.Produktroller;
import se.fk.mimer.producermodels.v2.model.produkt.RollIProdukt;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class RollIProduktTest
{
    Validator validator = inject( Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "RollIProduktTest" )
    @MethodSource("provideRollIProduktTestData")
    void rollIProduktIsValid( RollIProdukt rollIProdukt, int expectedResult )
    {
        Set<ConstraintViolation<RollIProdukt>> violations = validator.validate( rollIProdukt );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "RollIProduktSerializationTest")
    void RollIProduktIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/rolliprodukt.json" );
        DataObject dataObject = mapper.readValue( data, RollIProdukt.class );
        assertInstanceOf( RollIProdukt.class, dataObject );
    }

    private static Stream<Arguments> provideRollIProduktTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        UUID produktid = TestObjectUtil.getProdukt().getId();

        return Stream.of(
                Arguments.of(new RollIProdukt(), 4),
                Arguments.of(new RollIProdukt(null, 0, null, null, null), 4),
                Arguments.of(new RollIProdukt(uuid, 0, "1234567811234", null, null), 2),
                Arguments.of(new RollIProdukt(uuid, 0, "1234567811234", null, null), 2),
                Arguments.of(new RollIProdukt(uuid, 0, "1234567811234", produktid, null), 1),
                Arguments.of(new RollIProdukt(uuid, 0, "1234567811234", produktid, Produktroller.PRODUKTAGARE), 0)
        );
    }
}
