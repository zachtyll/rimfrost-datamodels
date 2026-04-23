package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class StallningstagenBosattningEllerArbeteISverige extends StallningstagandeIHandlaggningen
{
    public StallningstagenBosattningEllerArbeteISverige( UUID id, int version, ZonedDateTime from, ZonedDateTime tom,
                                                         Yrkandestatus yrkandestatus,
                                                         Collection<Person> avserPersoner, Yrkande avserYrkande,
                                                         Collection<Regel> godkandRegler,
                                                         Collection<Regel> avslagPaGrundAvRegler, Boolean bosatt,
                                                         Boolean arbetar,
                                                         Stallningstagande stallningstagande)
    {
        super( id, version, from, tom, yrkandestatus, avserPersoner, avserYrkande, godkandRegler,
                avslagPaGrundAvRegler, stallningstagande );
        this.bosatt = bosatt;
        this.arbetar = arbetar;
    }

    @NotNull
    private Boolean bosatt;
    @NotNull
    private Boolean arbetar;
}
