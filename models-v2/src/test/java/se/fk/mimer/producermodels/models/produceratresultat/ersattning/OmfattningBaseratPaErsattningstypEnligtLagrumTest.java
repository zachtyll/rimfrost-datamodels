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
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class OmfattningBaseratPaErsattningstypEnligtLagrumTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "OmfattningBaseratPaErsattningstypEnligtLagrumTest" )
    @MethodSource("provideOmfattningBaseratPaErsattningstypEnligtLagrumTestData")
    void omfattningBaseratPaErsattningstypEnligtLagrumIsValid( OmfattningBaseratPaErsattningstypEnligtLagrum omfattning, int expectedResult )
    {
        Set<ConstraintViolation<OmfattningBaseratPaErsattningstypEnligtLagrum>> violations = validator.validate(omfattning);
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "OmfattningBaseratPaErsattningstypEnligtLagrumSerializationTest" )
    void OmfattningBaseratPaErsattningstypEnligtLagrumIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/omfattningBaseratPaErsattningstypEnligtLagrum.json" );
        DataObject dataObject = mapper.readValue( data, OmfattningBaseratPaErsattningstypEnligtLagrum.class );
        assertInstanceOf( OmfattningBaseratPaErsattningstypEnligtLagrum.class, dataObject );
    }

    private static Stream<Arguments> provideOmfattningBaseratPaErsattningstypEnligtLagrumTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        ErsattningstypEnligtLagrum etyp = TestObjectUtil.getErsattningstypEnligtLagrum();

        return Stream.of(
                Arguments.of(new OmfattningBaseratPaErsattningstypEnligtLagrum(), 2),
                Arguments.of(new OmfattningBaseratPaErsattningstypEnligtLagrum(null, 0, null, 0.0, null), 2),
                Arguments.of(new OmfattningBaseratPaErsattningstypEnligtLagrum(uuid, 0, null, 50.0, null), 1),
                Arguments.of(new OmfattningBaseratPaErsattningstypEnligtLagrum(uuid, 0, "OmfattningBaseratPaErsattningstypEnligtLagrum", 50.0, null), 0),
                Arguments.of(new OmfattningBaseratPaErsattningstypEnligtLagrum(uuid, 0, "OmfattningBaseratPaErsattningstypEnligtLagrum", 100.0, etyp), 0)
        );
    }
}
