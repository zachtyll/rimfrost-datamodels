package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.referensdata.yrkande.beslut.Avslutstyper;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.beslut.Beslutsutfall;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.beslut.Versionsbeslutstyper;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Avslutstyp;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslut;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutsrad;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutstyp;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutsutfallstyp;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.UUID;

import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createIdTyp;

public class BeslutFixtures
{
    public BeslutFixtures()
    {
    }

    public static Avslutstyp createAvslutstyp()
    {
        return Avslutstyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .avslutstyp( Avslutstyper.SAKFRAGA_PAPROVAD )
                .build();
    }

    public static Beslutstyp createBeslutstyp()
    {
        return Beslutstyp.builder()
                .id( UUID.randomUUID() )
                .versionsbesluttyp( Versionsbeslutstyper.SLUTGILTIGT )
                .build();
    }

    public static Beslutsutfallstyp createBeslutsutfallstyp()
    {
        return Beslutsutfallstyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .beslutsutfall( Beslutsutfall.BEVILJAT )
                .build();
    }

    public static Beslutsrad createBeslutsrad()
    {
        return Beslutsrad.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .harSakfragaStallningstaganden( Collections.emptyList() )
                .beslutstyp( createBeslutstyp() )
                .beslutsutfall( createBeslutsutfallstyp() )
                .avslutstyp( createAvslutstyp() )
                .build();
    }

    public static Beslut createBeslut()

    {
        return Beslut.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .beslutsDatum( ZonedDateTime.now() )
                .beslutsfattare( createIdTyp() )
                .build();
    }
}
