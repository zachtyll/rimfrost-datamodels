package se.fk.mimer.datamodel.v1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
//@Constraint( validatedBy = FysiskPersonValidator.class )
@Target( { ElementType.TYPE } )
@Retention( RetentionPolicy.RUNTIME )
public @interface ValidFysiskPerson
{
    String message() default "FysiskPerson must have either a personnummer or kundid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
