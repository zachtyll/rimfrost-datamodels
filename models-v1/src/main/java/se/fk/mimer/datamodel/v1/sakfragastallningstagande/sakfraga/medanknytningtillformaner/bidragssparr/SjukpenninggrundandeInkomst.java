package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Periodisering;
import se.fk.mimer.datamodel.v1.inkomst.Inkomst;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.SakfragaIHandlaggningen;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class SjukpenninggrundandeInkomst extends SakfragaIHandlaggningen
{

    public SjukpenninggrundandeInkomst( UUID id, int version, ZonedDateTime from, ZonedDateTime tom,
                                        Yrkandestatus yrkandestatus, Collection<Person> avserPersoner,
                                        Collection<Regel> godkandRegel, Collection<Regel> avslagPaGrundAvRegel,
                                        Yrkande avserYrkande,
                                        se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga avserSakfraga,
                                        SFBInkomsttyp SFBInkomstTyp, Double SFBInkomstBelopp,
                                        ZonedDateTime SFBInkomstDatum, ZonedDateTime SFBInkomstFrom,
                                        ZonedDateTime SFBInkomstTom, Periodisering periodisering,
                                        Collection<Inkomst> avserInkomst )
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, godkandRegel, avslagPaGrundAvRegel, avserYrkande
                , avserSakfraga );
        this.SFBInkomstTyp = SFBInkomstTyp;
        this.SFBInkomstBelopp = SFBInkomstBelopp;
        this.SFBInkomstDatum = SFBInkomstDatum;
        this.SFBInkomstFrom = SFBInkomstFrom;
        this.SFBInkomstTom = SFBInkomstTom;
        this.periodisering = periodisering;
        this.avserInkomst = avserInkomst;
    }

    @NotNull
    private SFBInkomsttyp SFBInkomstTyp;
    @NotNull
    private Double SFBInkomstBelopp;
    @NotNull
    private ZonedDateTime SFBInkomstDatum;
    @NotNull
    private ZonedDateTime SFBInkomstFrom;
    @NotNull
    private ZonedDateTime SFBInkomstTom;
    @NotNull
    private Periodisering periodisering;
    @NotNull
    private Collection<Inkomst> avserInkomst;
}
