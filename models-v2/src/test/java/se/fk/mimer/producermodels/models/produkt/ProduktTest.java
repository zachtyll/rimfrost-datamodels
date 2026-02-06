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
import se.fk.mimer.producermodels.v2.model.produkt.Produkt;
import se.fk.mimer.producermodels.v2.model.produkt.EProduktnamn;
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

class ProduktTest
{
    Validator validator = inject( Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "ProduktTest" )
    @MethodSource("provideProduktTestData")
    void produktIsValid( Produkt produkt, int expectedResult )
    {
        Set<ConstraintViolation<Produkt>> violations = validator.validate( produkt );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "ProduktSerializationTest")
    void ProduktIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/produkt.json" );
        DataObject dataObject = mapper.readValue( data, Produkt.class );
        assertInstanceOf( Produkt.class, dataObject );
    }

    @Test
    @Tag( "ProduktCanAddAndRemoveErbjudandenTest" )
    void ProduktOwnsErbjudande()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        Produkt produkt = TestObjectUtil.getProdukt();
        int starterSize = produkt.getErbjudanden().size();

        DataObject res = produkt.addNewErbjudande( uuid, 0, "testerbjudande", EProduktnamn.UNDERHALLSSTOD, EErbjudande.BARNINKOMSTAVDRAG );
        assertInstanceOf( Erbjudande.class, res );
        assertEquals( starterSize + 1, produkt.getErbjudanden().size() );
        DataObject delres = produkt.removeErbjudande( uuid );
        assertInstanceOf( Erbjudande.class, delres);
        assertEquals( starterSize, produkt.getErbjudanden().size() );
    }

    private static Stream<Arguments> provideProduktTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        RollIProdukt[] roller = TestObjectUtil.getProdukt().getRoller();
        Map<UUID, Erbjudande> erbjudanden = TestObjectUtil.getErbjudandeMap();

        return Stream.of(
                Arguments.of(new Produkt(), 3),
                Arguments.of(new Produkt(null, 0, null, null, null), 3),
                Arguments.of(new Produkt(uuid, 0, null, null, null), 2),
                Arguments.of(new Produkt(uuid, 0, null, null, null), 2),
                Arguments.of(new Produkt(uuid, 0, EProduktnamn.FAMILJEBIDRAG, null, null), 1),
                Arguments.of(new Produkt(uuid, 0, EProduktnamn.FAMILJEBIDRAG, roller, null), 0),
                Arguments.of(new Produkt(uuid, 0, EProduktnamn.FAMILJEBIDRAG, roller, erbjudanden), 0)
        );
    }
}
