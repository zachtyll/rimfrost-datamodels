package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Periodisering;
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
public class StallningstagenKostnad extends StallningstagandeIHandlaggningen
{
    public StallningstagenKostnad( UUID id, int version, ZonedDateTime from, ZonedDateTime tom,
                                   Yrkandestatus yrkandestatus, Collection<Person> avserPersoner,
                                   Yrkande avserYrkande, Collection<Regel> godkandRegler,
                                   Collection<Regel> avslagPaGrundAvRegler,
                                   StallningstagnaKostnadstyper bedomdIKostnadTyp, Double bedomdKonstnadBelopp,
                                   ZonedDateTime bedomdKostnadDatum, ZonedDateTime bedomdKostnadFrom,
                                   ZonedDateTime bedomdKostnadTom, Periodisering periodisering,
                                   Stallningstagande stallningstagande)
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, avserYrkande, godkandRegler,
                avslagPaGrundAvRegler, stallningstagande );
        this.bedomdIKostnadTyp = bedomdIKostnadTyp;
        this.bedomdKonstnadBelopp = bedomdKonstnadBelopp;
        this.bedomdKostnadDatum = bedomdKostnadDatum;
        this.bedomdKostnadFrom = bedomdKostnadFrom;
        this.bedomdKostnadTom = bedomdKostnadTom;
        this.periodisering = periodisering;
    }

    @NotNull
    private StallningstagnaKostnadstyper bedomdIKostnadTyp;
    @NotNull
    private Double bedomdKonstnadBelopp;
    @NotNull
    private ZonedDateTime bedomdKostnadDatum;
    @NotNull
    private ZonedDateTime bedomdKostnadFrom;
    @NotNull
    private ZonedDateTime bedomdKostnadTom;
    @NotNull
    private Periodisering periodisering;
}
