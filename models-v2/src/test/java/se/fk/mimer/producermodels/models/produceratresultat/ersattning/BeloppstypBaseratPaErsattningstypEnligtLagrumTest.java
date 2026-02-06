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
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstypKategori;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.BeloppstypBaseratPaErsattningstypEnligtLagrum;
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

class BeloppstypBaseratPaErsattningstypEnligtLagrumTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag( "BeloppstypBaseratPaErsattningstypEnligtLagrumTest" )
    @MethodSource("provideBeloppstypBaseratPaErsattningstypEnligtLagerumTestData")
    void beloppstypBaseratPaErsattningstypEnligtLagrumIsValid( BeloppstypBaseratPaErsattningstypEnligtLagrum beloppstyp, int expectedResult )
    {
        Set<ConstraintViolation<BeloppstypBaseratPaErsattningstypEnligtLagrum>> violations = validator.validate(beloppstyp);
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "BeloppstypBaseratPaErsattningstypEnligtLagrumSerializationTest" )
    void BeloppstypBaseratPaErsattningstypEnligtLagrumIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/beloppstypBaseratPaErsattningstypEnligtLagrum.json" );
        DataObject dataObject = mapper.readValue( data, BeloppstypBaseratPaErsattningstypEnligtLagrum.class );
        assertInstanceOf( BeloppstypBaseratPaErsattningstypEnligtLagrum.class, dataObject );
    }

    private static Stream<Arguments> provideBeloppstypBaseratPaErsattningstypEnligtLagerumTestData()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        ErsattningstypEnligtLagrum etyp = TestObjectUtil.getErsattningstypEnligtLagrum();

        return Stream.of(
                Arguments.of(new BeloppstypBaseratPaErsattningstypEnligtLagrum(), 2),
                Arguments.of(new BeloppstypBaseratPaErsattningstypEnligtLagrum(null, 0, null, null, null, null), 2),
                Arguments.of(new BeloppstypBaseratPaErsattningstypEnligtLagrum(uuid, 0, null, null, null, null), 1),
                Arguments.of(new BeloppstypBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BeloppstypBaseratPaErsattningsgrundEnligtLagrum", null, null, null), 0),
                Arguments.of(new BeloppstypBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BeloppstypBaseratPaErsattningsgrundEnligtLagrum", EBeloppstypKategori.AS, null, null), 0),
                Arguments.of(new BeloppstypBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BeloppstypBaseratPaErsattningsgrundEnligtLagrum", EBeloppstypKategori.AS, EBeloppstyp.SANKTIONSTAK, null ), 0),
                Arguments.of(new BeloppstypBaseratPaErsattningstypEnligtLagrum(uuid, 0, "BeloppstypBaseratPaErsattningsgrundEnligtLagrum", EBeloppstypKategori.AS, EBeloppstyp.SANKTIONSTAK, etyp), 0)
        );
    }
}
