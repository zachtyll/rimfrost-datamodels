package se.fk.mimer.datamodel.v1.regel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Punkt extends Lagrum
{
    public Punkt( String individId, String punktSekvens, String punktBeskrivning, String referensTillAnnanForfattning )
    {
        super( individId );
        this.punktSekvens = punktSekvens;
        this.punktBeskrivning = punktBeskrivning;
        this.referensTillAnnanForfattning = referensTillAnnanForfattning;
    }
    @NotNull
    @NotBlank
    private String punktSekvens;
    @NotNull
    @NotBlank
    private String punktBeskrivning;
    @NotNull
    @NotBlank
    private String referensTillAnnanForfattning;
}
