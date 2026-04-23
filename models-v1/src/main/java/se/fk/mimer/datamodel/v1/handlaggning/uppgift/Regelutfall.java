package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.regelutfall.Regelutfallstyp;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
class Regelutfall
{
    @NotNull
    private Regelutfallstyp typ;

    public String getRegelutfallstyp()
    {
        return typ.getRegelutfall().toString();
    }
}
