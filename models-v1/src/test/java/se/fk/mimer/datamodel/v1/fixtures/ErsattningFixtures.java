package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.Periodisering;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.somgerrattentill.Beloppstyper;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.somgerrattentill.ersattning.Ersattning;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createPerson;
import static se.fk.mimer.datamodel.v1.fixtures.SakfragaFixtures.createSakfraga;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createYrkande;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createYrkandestatus;

public class ErsattningFixtures
{
    public ErsattningFixtures()
    {
    }

    public static Beloppstyper createBeloppstyp()
    {
        return Beloppstyper.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .beloppstyper( se.fk.mimer.datamodel.v1.referensdata.sakfraga.stallningstagande.Beloppstyper.GRUNDBELOPP )
                .build();
    }

    public static Periodisering createPeriodisering()
    {
        return Periodisering.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .periodisering( se.fk.mimer.datamodel.v1.referensdata.Periodisering.ENGANGS )
                .build();
    }

    public static Ersattning createErsattning()
    {
        return Ersattning.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .from( ZonedDateTime.now() )
                .tom( ZonedDateTime.now().plusDays( 1 ) )
                .yrkandestatus( createYrkandestatus() )
                .avserPersoner( List.of( createPerson() ) )
                .godkandRegler( Collections.emptyList() )
                .avslagPaGrundAvRegler( Collections.emptyList() )
                .faststallsForYrkande( UUID.randomUUID() )
                .avserSakfraga( createSakfraga() )
                .ersattningsBelopp( 100.0 )
                .beloppsTyp( createBeloppstyp() )
                .periodisering( createPeriodisering() )
                .omfattningProcent( 100 )
                .samordnasMedErsattningar( Collections.emptyList() )
                .build();
    }
}
