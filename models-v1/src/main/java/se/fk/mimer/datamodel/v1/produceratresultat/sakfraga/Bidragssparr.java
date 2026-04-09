package se.fk.mimer.datamodel.v1.produceratresultat.sakfraga;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.lagrum.Regel;
import se.fk.mimer.datamodel.v1.person.Person;
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
public class Bidragssparr extends Sakfraga
{
    public Bidragssparr( UUID id, int version, ZonedDateTime from, ZonedDateTime tom, Yrkandestatus status,
                         Collection<Regel> godkandRegel, Collection<Regel> avslagPaGrundAvRegel,
                         Collection<Person> avserPersoner, Yrkande avserYrkande,
                         se.fk.mimer.datamodel.v1.produkt.forman.Sakfraga avserSakfragor,
                         UUID produktId, GrundForBidragssparrutredning grundForUtredning,
                         AnledningIngenBidragssparr anledningIngenBidragssparr)
    {
        super(id, version, from, tom, status, godkandRegel, avslagPaGrundAvRegel, avserPersoner, avserYrkande, avserSakfragor);
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
