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
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.beslut.delgivning.Delgivning;
import se.fk.mimer.producermodels.v2.model.beslut.delgivning.Delgivningstyp;
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

class DelgivningTest
{
    Validator validator = inject(Validator.class);

    ObjectMapper mapper = new ObjectMapperProducer().getObjectMapper();

    @ParameterizedTest
    @Tag("DelgivningValidationTest")
    @MethodSource("provideDelgivningTestData")
    void delgivningIsValid( Delgivning delgivning, int expectedResult )
    {
        Set<ConstraintViolation<Delgivning>> violations = validator.validate( delgivning );
        assertEquals( expectedResult, violations.size() );
    }

    @Test
    @Tag( "DelgivningSerializationTest" )
    void delgivningIsSeriazable() throws JsonProcessingException
    {
        String data = TestdataUtil.readResourceFile( "dataobjects/delgivning.json" );
        DataObject dataObject = mapper.readValue( data, Delgivning.class );
        assertInstanceOf( Delgivning.class, dataObject );
    }

    private static Stream<Arguments> provideDelgivningTestData()
    {
        ZonedDateTime date = TestObjectUtil.getDate();
        UUID uuid = Generators.timeBasedEpochRandomGenerator().generate();
        UUID beslutId = Generators.timeBasedEpochGenerator().generate();

        return Stream.of(
                Arguments.of(new Delgivning(), 1),
                Arguments.of(new Delgivning(null, 0, null, null, null), 1),
                Arguments.of(new Delgivning(uuid, 0, null, null, null), 0),
                Arguments.of(new Delgivning(uuid, 0, null, null, null), 0),
                Arguments.of(new Delgivning(uuid, 0, date, null, null), 0),
                Arguments.of(new Delgivning(uuid, 0, date, Delgivningstyp.FORENKLAD, null), 0),
                Arguments.of(new Delgivning(uuid, 0, date, Delgivningstyp.FORENKLAD, beslutId), 0)
        );
    }
}
