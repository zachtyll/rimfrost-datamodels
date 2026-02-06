package se.fk.mimer.producermodels.models.beslut;

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
import se.fk.mimer.producermodels.v2.model.beslut.Beslut;
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.beslut.BeslutandeOrganisation;
import se.fk.mimer.producermodels.v2.model.beslut.Beslutstyp;
import se.fk.mimer.producermodels.v2.model.beslut.Beslutsutfall;
import se.fk.mimer.producermodels.utils.ObjectMapperProducer;
import se.fk.mimer.producermodels.utils.TestObjectUtil;
import se.fk.mimer.producermodels.utils.TestdataUtil;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

class BeslutTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag("BeslutValidationTest")
    @MethodSource("provideBeslutTestData")
    void beslutIsValid( Beslut beslut, int expectedResult) {
        Set<ConstraintViolation<Beslut>> violations = validator.validate( beslut );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "BeslutSerializationTest" )
    void BeslutIsSerializable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/beslut.json" );
        DataObject dataObject = mapper.readValue( data, Beslut.class );
        assertInstanceOf( Beslut.class, dataObject );
    }

    private static Stream<Arguments> provideBeslutTestData() {
        ZonedDateTime date  = TestObjectUtil.getDate();
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();

        return Stream.of(
                Arguments.of(new Beslut(), 2),
                Arguments.of(new Beslut(null,0,  null, null, null, null, null, null, null, null), 2),
                Arguments.of(new Beslut(uuid, 0, null, null, null, null, null, null, null, null), 1),
                Arguments.of(new Beslut(uuid, 0, null, null, null, null, null, null, null, null), 1),
                Arguments.of(new Beslut(uuid, 0, uuid, null, null, null, null, null, null, null), 0),
                Arguments.of(new Beslut(uuid, 0, uuid, date, null, null, null, null, null, null), 0),
                Arguments.of(new Beslut(uuid, 0, uuid, date, Beslutstyp.SLUTGILTIG, null, null, null, null, null), 0),
                Arguments.of(new Beslut(uuid, 0, uuid, date, Beslutstyp.SLUTGILTIG, Beslutsutfall.BEVILJAT, null, null, null, null), 0),
                Arguments.of(new Beslut(uuid, 0, uuid, date, Beslutstyp.SLUTGILTIG, Beslutsutfall.BEVILJAT, "kortnr", null, null, null), 0),
                Arguments.of(new Beslut(uuid, 0, uuid, date, Beslutstyp.INTERMISTISK, Beslutsutfall.BEVILJAT, "kortnr", null, null, null), 0),
                Arguments.of(new Beslut(uuid, 0, uuid, date, Beslutstyp.INTERMISTISK, Beslutsutfall.AVSLAG, "kortnr", null, BeslutandeOrganisation.FORSAKRINGSKASSAN, null), 0),
                Arguments.of(new Beslut(uuid, 0, uuid, date, Beslutstyp.INTERMISTISK, Beslutsutfall.AVSLAG, "kortnr", null, BeslutandeOrganisation.FORSAKRINGSKASSAN, "avslagsanledning"), 0)
        );
    }
}
