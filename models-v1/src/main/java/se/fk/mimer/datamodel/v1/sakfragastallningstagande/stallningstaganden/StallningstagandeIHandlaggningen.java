package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

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
public abstract class StallningstagandeIHandlaggningen extends SakfragaStallningstagande
{
    public StallningstagandeIHandlaggningen( @NotNull UUID id, @NotNull int version, @NotNull ZonedDateTime from, ZonedDateTime tom,
                                             @NotNull Yrkandestatus yrkandestatus, @NotNull Collection<Person> avserPersoner,
                                             @NotNull Yrkande avserYrkande, @NotNull Collection<Regel> godkandRegler,
                                             @NotNull Collection<Regel> avslagPaGrundAvRegler,
                                             @NotNull Stallningstagande stallningstagande )
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, avserYrkande, godkandRegler,
                avslagPaGrundAvRegler );
        this.stallningstagande = stallningstagande;
    }

    @NotNull
    private Stallningstagande stallningstagande;
}
