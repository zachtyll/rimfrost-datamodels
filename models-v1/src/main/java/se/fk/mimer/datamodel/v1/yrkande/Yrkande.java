package se.fk.mimer.datamodel.v1.yrkande;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudande;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.person.Persontyp;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.YrkanderollTyper;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.SakfragaStallningstagande;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslut;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Yrkande
{
    @NotNull
    private UUID id;
    @NotNull
    @Min( 1 )
    private int version;
    @NotNull
    private Collection<Beslut> avserBeslut;
    @NotNull
    private SakfragaStallningstagande avserSakfragaStallningstagande;
    @NotNull
    private List<RollIYrkande> rollerIYrkandet;
    @NotNull
    private List<Handlaggning> hanterasIHandlaggningar;
    private Beslut avserBesvarAvBeslut;
    @NotNull
    private Yrkandestatus yrkandeStatus;
    private Avsiktstyp avsikt;
    @NotNull
    private ZonedDateTime yrkandeDatum;
    @NotNull
    private Erbjudande avserErbjudande;
    @NotNull
    private ZonedDateTime from;
    private ZonedDateTime tom;

    public Optional<List<Handlaggning>> getHanterasIHandlaggningar()
    {
        return Optional.ofNullable( hanterasIHandlaggningar );
    }

    public Optional<Beslut> getAvserBesvarAvBeslut()
    {
        return Optional.ofNullable( avserBesvarAvBeslut );
    }

    public Optional<Avsiktstyp> getAvsikt()
    {
        return Optional.ofNullable( avsikt );
    }

    public Optional<ZonedDateTime> getTom()
    {
        return Optional.ofNullable( tom );
    }

    public Persontyp personenYrkandetAvser()
    {
        Optional<RollIYrkande> roll = rollerIYrkandet.stream().filter( f -> f.getYrkanderoll().getYrkanderollTyp().equals( YrkanderollTyper.BAS_DEN_SOM_SAKFRAGAN_AVSER ) ).findAny();
        return roll.map( RollIYrkande::getAvserPersontyp ).orElse( null );
    }
}
