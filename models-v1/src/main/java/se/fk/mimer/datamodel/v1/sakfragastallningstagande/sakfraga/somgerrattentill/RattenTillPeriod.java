package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.somgerrattentill;

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
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class RattenTillPeriod extends SakfragaIHandlaggningen
{
    public RattenTillPeriod( UUID id, int version, ZonedDateTime from, ZonedDateTime tom, Yrkandestatus yrkandestatus
            , Collection<Person> avserPersoner, Collection<Regel> godkandRegel,
                             Collection<Regel> avslagPaGrundAvRegel, Yrkande faststallsForYrkande,
                             se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga avserSakfraga, Double omfattningIProcent )
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, godkandRegel, avslagPaGrundAvRegel, faststallsForYrkande
                , avserSakfraga );
        this.omfattningIProcent = omfattningIProcent;
    }

    @NotNull
    private Double omfattningIProcent;
}
