package se.fk.mimer.datamodel.v1.person.adress;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public abstract class Adress
{
    public Adress( UUID id, int version )
    {
        this.id = id;
        this.version = version;
    }

    @NotNull
    private UUID id;
    @NotNull
    private int version;
}
