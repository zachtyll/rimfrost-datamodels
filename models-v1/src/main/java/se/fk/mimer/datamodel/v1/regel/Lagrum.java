package se.fk.mimer.datamodel.v1.regel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public abstract class Lagrum
{
    public Lagrum( String individId )
    {
        this.individId = individId;
    }
    @NotNull
    @NotBlank
    private String individId;
}
