package se.fk.mimer.datamodel.v1.organisation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.OrganisationsFixtures;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganisationValidationTest
{
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setupValidator()
    {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown()
    {
        factory.close();
    }

    @Test
    void valid_organisationsForm_hasNoViolations()
    {
        OrganisationsForm form = OrganisationsFixtures.createOrganisationsForm();
        var violations = validator.validate( form );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }

    @Test
    void valid_juridiskFormkod_hasNoViolations()
    {
        JuridiskFormkod jur = OrganisationsFixtures.createJuridiskFormkod();
        var violations = validator.validate( jur );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }

    @Test
    void valid_organisationsIdentitet_hasNoViolations()
    {
        OrganisationsIdentitet identitet = OrganisationsFixtures.createOrganisationsIdentitet();
        var violations = validator.validate( identitet );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }

    @Test
    void valid_organisation_hasNoViolations()
    {
        Organisation org = OrganisationsFixtures.createOrganisation();
        var violations = validator.validate( org );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }

    @Test
    void valid_organisationsenhet_hasNoViolations()
    {
        Organisationsenhet org = OrganisationsFixtures.createOrganisationsenhet();
        var violations = validator.validate( org );
        assertTrue( violations.isEmpty(), () -> "Violations: " + violations );
    }
}
