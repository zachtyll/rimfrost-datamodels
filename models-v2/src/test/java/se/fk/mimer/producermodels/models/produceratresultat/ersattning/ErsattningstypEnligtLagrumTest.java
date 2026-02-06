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
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.EErsattningstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ELagrumForErsattningstyp;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class ErsattningstypEnligtLagrumTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "ErsattningEnligtLagrumValidationTest" )
    @MethodSource("provideErsattningstypEnligtLagrumTestData")
    void ersattningstypEnligtLagrumIsValid( ErsattningstypEnligtLagrum ersattningstypEnligtLagrum, int expectedResult )
    {
        Set<ConstraintViolation<ErsattningstypEnligtLagrum>> violations = validator.validate( ersattningstypEnligtLagrum );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "ErsattningEnligtLagrumSerializationTest" )
    void ErsattningstypEnligtLagrumIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/ersattningstypEnligtLagrum.json" );
        DataObject dataObject = mapper.readValue( data, ErsattningstypEnligtLagrum.class );
        assertInstanceOf( ErsattningstypEnligtLagrum.class, dataObject );
    }

    private static Stream<Arguments> provideErsattningstypEnligtLagrumTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();

        return Stream.of(
                Arguments.of(new ErsattningstypEnligtLagrum(), 3),
                Arguments.of(new ErsattningstypEnligtLagrum(null, 0, null, null, null), 3),
                Arguments.of(new ErsattningstypEnligtLagrum(uuid, 0, null, null, null), 2),
                Arguments.of(new ErsattningstypEnligtLagrum(uuid, 0, "ErsattningstypEnligtLagrum", null, null), 1),
                Arguments.of(new ErsattningstypEnligtLagrum(uuid, 0, "ErsattningstypEnligtLagrum", EErsattningstyp.BEGRAVNINGSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA, null ), 0),
                Arguments.of(new ErsattningstypEnligtLagrum(uuid, 0, "ErsattningstypEnligtLagrum", EErsattningstyp.BEGRAVNINGSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA, ELagrumForErsattningstyp.LAGX ), 0)
        );
    }
}
