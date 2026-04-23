package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr.bidragssparr;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.SakfragaIHandlaggningen;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Setter
@Getter
@SuperBuilder
public class Bidragssparr extends SakfragaIHandlaggningen
{
    public Bidragssparr( UUID id, int version, ZonedDateTime from, ZonedDateTime tom, Yrkandestatus status,
                         Collection<Person> avserPersoner,
                         Collection<Regel> godkandRegel, Collection<Regel> avslagPaGrundAvRegel, Yrkande avserYrkande,
                         se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga avserSakfragor,
                         UUID produktId, GrundForBidragssparrutredning grundForUtredning,
                         AnledningIngenBidragssparr anledningIngenBidragssparr )
    {
        super( id, version, from, tom, status, avserPersoner, godkandRegel, avslagPaGrundAvRegel, avserYrkande,
                avserSakfragor );
        this.formanssparr = produktId;
        this.grundForBidragssparrutredning = grundForUtredning;
        this.anledningIngenBidragssparr = anledningIngenBidragssparr;
    }

    @NotNull
    private UUID formanssparr;
    private GrundForBidragssparrutredning grundForBidragssparrutredning;
    private AnledningIngenBidragssparr anledningIngenBidragssparr;

    public Optional<GrundForBidragssparrutredning> getGrundForBidragssparrutredning()
    {
        return Optional.ofNullable( grundForBidragssparrutredning );
    }

    public Optional<AnledningIngenBidragssparr> getAnledningIngenBidragssparr()
    {
        return Optional.ofNullable( anledningIngenBidragssparr );
    }
}
