package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.SakfragaStallningstagande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public abstract class SakfragaIHandlaggningen extends SakfragaStallningstagande
{
    public SakfragaIHandlaggningen( UUID id, int version, ZonedDateTime from, ZonedDateTime tom, Yrkandestatus yrkandestatus,
                                    Collection<Person> avserPersoner, Collection<Regel> godkandRegel,
                                    Collection<Regel> avslagPaGrundAvRegel, Yrkande faststallsForYrkande,
                                    se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga avserSakfraga )
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, faststallsForYrkande, godkandRegel, avslagPaGrundAvRegel );
        this.avserSakfraga = avserSakfraga;
    }

    @NotNull
    private se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga avserSakfraga;
}
