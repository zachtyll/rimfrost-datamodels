package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr;

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
public class Betalningsbelopp extends SakfragaIHandlaggningen
{
    public Betalningsbelopp( UUID id, int version, ZonedDateTime from, ZonedDateTime tom, Yrkandestatus yrkandestatus
            , Collection<Person> avserPersoner, Collection<Regel> godkandRegel,
                             Collection<Regel> avslagPaGrundAvRegel, Yrkande avserYrkande,
                             se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga avserSakfraga )
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, godkandRegel, avslagPaGrundAvRegel, avserYrkande
                , avserSakfraga );
    }
}
