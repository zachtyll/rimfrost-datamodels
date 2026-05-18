package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill.ersattning;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Berakningsgrunder;

@Jacksonized
@Getter
@Setter
@Builder
class Berakningsgrund
{
    Berakningsgrund( Berakningsgrunder berakningsgrunder )
    {
        this.berakningsgrunder = berakningsgrunder;
    }

    @NotNull
    private Berakningsgrunder berakningsgrunder;

    public String getVarde()
    {
        return berakningsgrunder.getBerakningsgrunder().toString();
    }
}
