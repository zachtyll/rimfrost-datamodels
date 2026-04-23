package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Periodisering;
import se.fk.mimer.datamodel.v1.inkomst.Inkomst;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class StallningstagenInkomst extends StallningstagandeIHandlaggningen
{
    public StallningstagenInkomst( UUID id, int version, ZonedDateTime from, ZonedDateTime tom,
                                   Yrkandestatus yrkandestatus, Collection<Person> avserPersoner,
                                   Yrkande avserYrkande, Collection<Regel> godkandRegler,
                                   Collection<Regel> avslagPaGrundAvRegler,
                                   StallningstagnaInkomsttyper bedomdInkomstTyp, Double bedomdInkomstBelopp,
                                   ZonedDateTime bedomdInkomstDatum, ZonedDateTime bedomdInkomstFrom,
                                   ZonedDateTime bedomdInkomstTom, Periodisering periodisering, Collection<Inkomst> avserInkomst,
                                   Stallningstagande stallningstagande)
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, avserYrkande, godkandRegler,
                avslagPaGrundAvRegler, stallningstagande );
        this.bedomdInkomstTyp = bedomdInkomstTyp;
        this.bedomdInkomstBelopp = bedomdInkomstBelopp;
        this.bedomdInkomstDatum = bedomdInkomstDatum;
        this.bedomdInkomstFrom = bedomdInkomstFrom;
        this.bedomdInkomstTom = bedomdInkomstTom;
        this.periodisering = periodisering;
        this.avserInkomst = avserInkomst;
    }

    @NotNull
    private StallningstagnaInkomsttyper bedomdInkomstTyp;
    @NotNull
    private Double bedomdInkomstBelopp;
    @NotNull
    private ZonedDateTime bedomdInkomstDatum;
    @NotNull
    private ZonedDateTime bedomdInkomstFrom;
    @NotNull
    private ZonedDateTime bedomdInkomstTom;
    @NotNull
    private Periodisering periodisering;
    @NotNull
    private Collection<Inkomst> avserInkomst;
}
