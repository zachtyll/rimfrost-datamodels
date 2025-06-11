package se.fk.mimer.producermodels.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import se.fk.mimer.producermodels.FysiskPerson;

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
