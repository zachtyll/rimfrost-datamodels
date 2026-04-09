package se.fk.mimer.datamodel.v1.produceratresultat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.lagrum.Regel;
import se.fk.mimer.datamodel.v1.person.Person;
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
public abstract class SakfragaBedomning
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private ZonedDateTime from;
    private ZonedDateTime tom;
    @NotNull
    private Yrkandestatus status;
    @NotNull
    @NotEmpty
    private Collection<Person> avserPersoner;
    @NotNull
    private Yrkande avserYrkande;
    @NotNull
    private Collection<Regel> godkandRegler;
    @NotNull
    private Collection<Regel> avslagPaGrundAvRegler;

    public Optional<ZonedDateTime> getTom()
    {
        return Optional.ofNullable( tom );
    }
}
