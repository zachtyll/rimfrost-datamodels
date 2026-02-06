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
import se.fk.mimer.producermodels.v2.model.produkt.EErbjudande;
import se.fk.mimer.producermodels.v2.model.produkt.Erbjudande;
import se.fk.mimer.producermodels.v2.model.produkt.EProduktnamn;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class ErbjudandeTest
{
    Validator validator = inject( Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "ErbjudandeTest" )
    @MethodSource("provideErbjudandeTestData")
    void erbjudandeIsValid( Erbjudande erbjudande, int expectedResult )
    {
        Set<ConstraintViolation<Erbjudande>> violations = validator.validate( erbjudande );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "ErbjudandeSerializationTest")
    void ErbjudandeIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/erbjudande.json" );
        DataObject dataObject = mapper.readValue( data, Erbjudande.class );
        assertInstanceOf( Erbjudande.class, dataObject );
    }

    private static Stream<Arguments> provideErbjudandeTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();

        return Stream.of(
                Arguments.of(new Erbjudande(), 3),
                Arguments.of(new Erbjudande(null, 0, null, null, null), 3),
                Arguments.of(new Erbjudande(uuid, 0, null, null, null), 2),
                Arguments.of(new Erbjudande(uuid, 0, null, null, null), 2),
                Arguments.of(new Erbjudande(uuid, 0, "Testerbjudande", null, null), 2),
                Arguments.of(new Erbjudande(uuid, 0, "Testerbjudande", EProduktnamn.FAMILJEBIDRAG, null), 1),
                Arguments.of(new Erbjudande(uuid, 0, "Testerbjudande", EProduktnamn.ARBETSMARKNADSPOLITISKA_INSATSER, EErbjudande.BOSTADSERSATTNING), 0)
        );
    }
}
