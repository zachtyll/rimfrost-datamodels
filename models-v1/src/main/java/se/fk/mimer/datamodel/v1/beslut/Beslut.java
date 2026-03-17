package se.fk.mimer.datamodel.v1.beslut;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.lagrum.Lagrum;
import se.fk.mimer.datamodel.v1.referensdata.beslut.BeslutandeOrganisation;
import se.fk.mimer.datamodel.v1.referensdata.beslut.Beslutstyp;
import se.fk.mimer.datamodel.v1.referensdata.beslut.Beslutsutfall;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
public class Beslut
{
    @Builder
    public Beslut( UUID id, int revision, UUID avserKundbehov, ZonedDateTime beslutsdatum, Beslutstyp beslutstyp,
                   Beslutsutfall beslutsutfall, String beslutsfattareId, Lagrum beslutEnligtLagrum, BeslutandeOrganisation beslutandeOrganisation,
                   String avslagsAnledning)
    {
        this.id = id;
        this.revision = revision;
        this.avserKundbehov = avserKundbehov;
        this.beslutsdatum = beslutsdatum;
        this.beslutstyp = beslutstyp;
        this.beslutsutfall = beslutsutfall;
        this.beslutsfattareId = beslutsfattareId;
        this.beslutEnligtLagrum = beslutEnligtLagrum;
        this.beslutandeOrganisation = beslutandeOrganisation;
        this.avslagsAnledning = avslagsAnledning;
    }
    @NotNull( message = "Beslut must have a proper ID." )
    @Getter
    private UUID id;

    @NotNull
    @Getter
    private int revision;

    @NotNull( message = "Beslut must have an avserKundbehov" )
    @Getter
    private UUID avserKundbehov;

    private ZonedDateTime beslutsdatum;

    private Beslutstyp beslutstyp;

    private Beslutsutfall beslutsutfall;

    private String beslutsfattareId;

    private Lagrum beslutEnligtLagrum;

    private BeslutandeOrganisation beslutandeOrganisation;

    private String avslagsAnledning;

    public Optional<ZonedDateTime> getBeslutsdatum()
    {
        return Optional.ofNullable( beslutsdatum );
    }

    public Optional<Beslutstyp> getBeslutstyp()
    {
        return Optional.ofNullable( beslutstyp );
    }

    public Optional<Beslutsutfall> getBeslutsutfall()
    {
        return Optional.ofNullable( beslutsutfall );
    }

    public Optional<String> getBeslutsfattareId()
    {
        return Optional.ofNullable( beslutsfattareId );
    }

    public Optional<Lagrum> getBeslutEnligtLagrum()
    {
        return Optional.ofNullable( beslutEnligtLagrum );
    }

    public Optional<BeslutandeOrganisation> getBeslutandeOrganisation()
    {
        return Optional.ofNullable( beslutandeOrganisation );
    }

    public Optional<String> getAvslagsAnledning()
    {
        return Optional.ofNullable( avslagsAnledning );
    }
}
