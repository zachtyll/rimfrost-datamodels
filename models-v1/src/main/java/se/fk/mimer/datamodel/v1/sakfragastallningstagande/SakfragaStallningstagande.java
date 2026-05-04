package se.fk.mimer.datamodel.v1.sakfragastallningstagande;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public abstract class SakfragaStallningstagande
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private ZonedDateTime from;
    private ZonedDateTime tom;
    @NotNull
    private Yrkandestatus yrkandestatus;
    @NotNull
    private Collection<Person> avserPersoner;
    @NotNull
    private UUID faststallsForYrkande;
    @NotNull
    private Collection<Regel> godkandRegler;
    @NotNull
    private Collection<Regel> avslagPaGrundAvRegler;

    public Optional<ZonedDateTime> getTom()
    {
        return Optional.ofNullable( tom );
    }
}
