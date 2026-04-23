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
public class Stycke extends Lagrum
{
    public Stycke( String individId, String styckeBeskrivning, String referensTillAnnanForfattning )
    {
        super( individId );
        this.styckeBeskrivning = styckeBeskrivning;
        this.referensTillAnnanForfattning = referensTillAnnanForfattning;
    }
    @NotNull
    @NotBlank
    private String styckeBeskrivning;
    @NotNull
    @NotBlank
    private String referensTillAnnanForfattning;
}
