package se.fk.mimer.datamodel.v1.beslut;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.BeslutFixtures;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Avslutstyp;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslut;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutstyp;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutsutfallstyp;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeslutValidationTest
{
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        factory.close();
    }

    @Test
    void valid_avslutstyp_hasNoViolations() {
        Avslutstyp avslut = BeslutFixtures.createAvslutstyp();
        var violations = validator.validate( avslut );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

    @Test
    void valid_beslutstyp_hasNoViolations() {
        Beslutstyp beslutstyp = BeslutFixtures.createBeslutstyp();
        var violations = validator.validate( beslutstyp );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

    @Test
    void valid_beslutsutfallstyp_hasNoViolations() {
        Beslutsutfallstyp typ = BeslutFixtures.createBeslutsutfallstyp();
        var violations = validator.validate( typ );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

    @Test
    void valid_beslut_hasNoViolations() {
        Beslut beslut  = BeslutFixtures.createBeslut();
        var violations = validator.validate( beslut );
        assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
    }

    @Disabled( "Skipped until ownership of Beslutsrader is sorted out" )
    @Test
    void beslut_ownsBeslutsrader()
    {
        Beslut beslut = BeslutFixtures.createBeslut();
        assertEquals( 0, beslut.getBeslutsrader().size() );
        UUID id = UUID.randomUUID();
        beslut.addBeslutsrad( id, 1, Collections.emptyList() );
        assertEquals( 1, beslut.getBeslutsrader().size() );
        beslut.removeBeslutsad( id );
        assertEquals( 0, beslut.getBeslutsrader().size() );
    }
}
