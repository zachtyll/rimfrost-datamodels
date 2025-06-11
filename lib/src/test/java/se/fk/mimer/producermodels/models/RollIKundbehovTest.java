package se.fk.mimer.producermodels.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.fk.mimer.producermodels.RollIKundbehov;

import java.util.Set;
import java.util.UUID;
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
        assertEquals( violations.size(), expectedResult );
    }

    private static Stream<Arguments> provideRollIKundbehovTestData() {
        UUID uuid = UUID.randomUUID();
        return Stream.of(
                Arguments.of(new RollIKundbehov(), 2),
                Arguments.of(new RollIKundbehov( null, null, true), 2),
                Arguments.of(new RollIKundbehov( null ,null, false), 2),
                Arguments.of(new RollIKundbehov( null, "yrkande", true), 1),
                Arguments.of(new RollIKundbehov( null, "yrkande", false), 1),
                Arguments.of(new RollIKundbehov( "Kundid", null, true), 1),
                Arguments.of(new RollIKundbehov( "Kundid", null, false), 1),
                Arguments.of(new RollIKundbehov( "Kundid", "yrkande", true), 0),
                Arguments.of(new RollIKundbehov( "Kundid", "yrkande", false), 0)

        );
    }
}
