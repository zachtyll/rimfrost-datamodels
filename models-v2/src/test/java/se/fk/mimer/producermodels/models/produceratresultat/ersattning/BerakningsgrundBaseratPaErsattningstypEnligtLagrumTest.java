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
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.EBerakningsgrund;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.EBerakningsgrundRegel;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.BerakningsgrundBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class BerakningsgrundBaseratPaErsattningstypEnligtLagrumTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "BerakningsgrundBaseratPaErsattningstypEnligtLagrumTest" )
    @MethodSource("provideBerakningsgrundBaseratPaErsattningstypEnligtLagrumTestData")
    void berakningsgrundBaseratPaErsattningstypEnligtLagrumIsValid( BerakningsgrundBaseratPaErsattningstypEnligtLagrum berakningsgund, int epxectedResult )
    {
        Set<ConstraintViolation<BerakningsgrundBaseratPaErsattningstypEnligtLagrum>> violations = validator.validate( berakningsgund );
        assertEquals( epxectedResult, violations.size() );
    }

    @Test
    @Tag( "BerakningsgrundBaseratPaErsattningstypEnligtLagrumSerializationTest" )
    void BerakningsgrundBaseratPaErsattningstypEnligtLagrumIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/berakningsgrundBaseratPaErsattningstypEnligtLagrum.json" );
        DataObject dataObject = mapper.readValue( data, BerakningsgrundBaseratPaErsattningstypEnligtLagrum.class );
        assertInstanceOf( BerakningsgrundBaseratPaErsattningstypEnligtLagrum.class, dataObject );
    }

    private static Stream<Arguments> provideBerakningsgrundBaseratPaErsattningstypEnligtLagrumTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        ErsattningstypEnligtLagrum etyp = TestObjectUtil.getErsattningstypEnligtLagrum();

        return Stream.of(
                Arguments.of(new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(), 2),
                Arguments.of(new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(null, 0, null, null, null, null), 2),
                Arguments.of(new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(uuid, 0, null, null, null, null), 1),
                Arguments.of(new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BerakningsgrundBaseratPaErsattningstypEnligtLagrum", null, null, null), 0),
                Arguments.of(new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BerakningsgrundBaseratPaErsattningstypEnligtLagrum", EBerakningsgrundRegel.AS, null, null), 0),
                Arguments.of(new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BerakningsgrundBaseratPaErsattningstypEnligtLagrum", EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, null), 0),
                Arguments.of(new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BerakningsgrundBaseratPaErsattningstypEnligtLagrum", EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, etyp), 0)
        );
    }
}
