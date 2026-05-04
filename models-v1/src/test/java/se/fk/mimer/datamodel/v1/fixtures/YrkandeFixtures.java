package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.referensdata.yrkande.Avsiktstyper;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.YrkandeStatus;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.Yrkanderoller;
import se.fk.mimer.datamodel.v1.yrkande.Avsiktstyp;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslut;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollerIYrkande;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static se.fk.mimer.datamodel.v1.fixtures.ErbjudandeFixtures.createErbjudande;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createIdTyp;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createPerson;

public class YrkandeFixtures
{
    public YrkandeFixtures()
    {
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

    public static Avsiktstyp createAvsiktstyp()
    {
        return Avsiktstyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .avsiktstyp( Avsiktstyper.NY )
                .build();
    }

    public static RollerIYrkande createRollerIYrkande()
    {
        return RollerIYrkande.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .yrkanderoll( Yrkanderoller.BAS_SOKANDE )
                .build();
    }

    public static RollIYrkande createRollIYrkande()
    {
        return RollIYrkande.builder()
                .individ( createIdTyp() )
                .roll( createRollerIYrkande() )
                .avserPerson( createPerson() )
                .avserYrkande( createYrkande() )
                .build();
    }

    public static Yrkandestatus createYrkandestatus()
    {
        return Yrkandestatus.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .status( YrkandeStatus.YRKAT )
                .build();
    }

    public static Yrkande createYrkande()
    {
        return Yrkande.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .avserBeslut( List.of( createBeslut() ) )
                .avserSakfragaStallningstagande( BidragssparrFixtures.createBidragssparr() )
                .avsikt( createAvsiktstyp() )
                .yrkandeDatum( ZonedDateTime.now() )
                .avserErbjudande( createErbjudande() )
                .avserBeslut( Collections.emptyList() )
                .hanterasIHandlaggningar( Collections.emptyList() )
                .rollerIYrkandet( List.of() )
                .yrkandeStatus( createYrkandestatus() )
                .yrkandeFrom( ZonedDateTime.now() )
                .yrkandeTom( ZonedDateTime.now().plusDays( 1 ) )
                .build();
    }
}
