package se.fk.mimer.datamodel.v1.yrkande.beslut;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.SakfragaStallningstagande;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Beslutsrad
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Collection<SakfragaStallningstagande> harSakfragaStallningstaganden;
    private Beslutstyp beslutstyp;
    private Beslutsutfallstyp beslutsutfall;
    private Avslutstyp avslutstyp;

    public Optional<Beslutstyp> getBeslutstyp()
    {
        return Optional.ofNullable( beslutstyp );
    }
    public Optional<Beslutsutfallstyp> getBeslutsutfallstyp()
    {
        return Optional.ofNullable( beslutsutfall );
    }
    public Optional<Avslutstyp> getAvslutstyp()
    {
        return Optional.ofNullable( avslutstyp );
    }
}
