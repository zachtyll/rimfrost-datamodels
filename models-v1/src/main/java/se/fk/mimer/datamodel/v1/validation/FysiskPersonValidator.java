package se.fk.mimer.datamodel.v1.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;

public class FysiskPersonValidator implements ConstraintValidator<ValidFysiskPerson, FysiskPerson>
{
    @Override
    public boolean isValid( FysiskPerson person, ConstraintValidatorContext context )
    {
        boolean pnrMissingOrEmpty = person.getPersonnummer()
                .map(String::isEmpty)
                .orElse(true);

        return !(pnrMissingOrEmpty && person.getKundid() == null);
    }
}
