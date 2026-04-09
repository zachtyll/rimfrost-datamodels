package se.fk.mimer.datamodel.v1.produceratresultat.sakfraga;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.lagrum.Regel;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.produceratresultat.SakfragaBedomning;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public abstract class Sakfraga extends SakfragaBedomning
{
    public Sakfraga( UUID id, int version, ZonedDateTime from, ZonedDateTime tom, Yrkandestatus yrkandestatus,
                     Collection<Regel> godkandRegel, Collection<Regel> avslagPaGrundAvRegel, Collection<Person> avserPersoner,
                     Yrkande avserYrkande, se.fk.mimer.datamodel.v1.produkt.forman.Sakfraga avserSakfraga )
    {
        super(id, version, from, tom, yrkandestatus, avserPersoner, avserYrkande, godkandRegel, avslagPaGrundAvRegel);
        this.avserSakfraga = avserSakfraga;
    }

    @NotNull
    private se.fk.mimer.datamodel.v1.produkt.forman.Sakfraga avserSakfraga;
}
