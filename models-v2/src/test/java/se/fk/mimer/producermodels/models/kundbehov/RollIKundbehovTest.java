package se.fk.mimer.producermodels.models.kundbehov;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollerIKundbehov;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;


class RollIKundbehovTest
{
    Validator validator = inject(Validator.class);

    @ParameterizedTest()
    @Tag("RollIKundbehovValidationTest")
    @MethodSource("provideRollIKundbehovTestData")
    void rollIKundbehovIsValid( RollIKundbehov rollIKundbehov, int expectedResult) {
        Set<ConstraintViolation<RollIKundbehov>> violations = validator.validate( rollIKundbehov );
        assertEquals( expectedResult, violations.size() );
    }

    private static Stream<Arguments> provideRollIKundbehovTestData() {
        return Stream.of(
                Arguments.of(new RollIKundbehov(), 0),
                Arguments.of(new RollIKundbehov( null, null, true), 0),
                Arguments.of(new RollIKundbehov( null,null, false), 0),
                Arguments.of(new RollIKundbehov( null, RollerIKundbehov.BOFORALDER, true), 0),
                Arguments.of(new RollIKundbehov( null, RollerIKundbehov.BOFORALDER, false), 0),
                Arguments.of(new RollIKundbehov( "Kundid", null, true), 0),
                Arguments.of(new RollIKundbehov( "Kundid", null, false), 0),
                Arguments.of(new RollIKundbehov( "Kundid", RollerIKundbehov.BOFORALDER, true), 0),
                Arguments.of(new RollIKundbehov( "Kundid", RollerIKundbehov.BOFORALDER, false), 0)

        );
    }
}
