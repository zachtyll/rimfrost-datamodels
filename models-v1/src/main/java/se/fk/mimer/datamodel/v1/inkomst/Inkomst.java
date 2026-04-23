package se.fk.mimer.datamodel.v1.inkomst;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public abstract class Inkomst
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private FysiskPerson avserFysiskPerson;
}
