package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.referensdata.yrkande.Avsiktstyper;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.YrkandeStatus;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.YrkanderollTyper;
import se.fk.mimer.datamodel.v1.yrkande.Avsiktstyp;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;
import se.fk.mimer.datamodel.v1.yrkande.roller.Yrkanderoll;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static se.fk.mimer.datamodel.v1.fixtures.ErbjudandeFixtures.createErbjudande;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createIdTyp;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createPersontyp;

public class YrkandeFixtures
{
    public YrkandeFixtures()
    {
    }

    public static Avsiktstyp createAvsiktstyp()
    {
        return Avsiktstyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .avsiktstyp( Avsiktstyper.NY )
                .build();
    }

    public static Yrkanderoll createRollerIYrkande()
    {
        return Yrkanderoll.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .yrkanderollTyp( YrkanderollTyper.BAS_SOKANDE )
                .build();
    }

    public static Yrkanderoll createRollerPersonenYrkandetAvser()
    {
        return Yrkanderoll.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .yrkanderollTyp( YrkanderollTyper.BAS_DEN_SOM_SAKFRAGAN_AVSER )
                .build();
    }

    public static RollIYrkande createRollIYrkande()
    {
        return RollIYrkande.builder()
                .id( UUID.randomUUID() )
                .individ( createIdTyp() )
                .yrkanderoll( createRollerIYrkande() )
                .avserPersontyp( createPersontyp() )
                .avserYrkande( UUID.randomUUID() )
                .build();
    }

    public static RollIYrkande createRollPersonenYrkandetAvser()
    {
        return RollIYrkande.builder()
                .id( UUID.randomUUID() )
                .individ( createIdTyp() )
                .yrkanderoll( createRollerPersonenYrkandetAvser() )
                .avserPersontyp( createPersontyp() )
                .avserYrkande( UUID.randomUUID() )
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
                .avserBeslut( List.of( BeslutFixtures.createBeslut() ) )
                .avserSakfragaStallningstagande( BidragssparrFixtures.createBidragssparr() )
                .avsikt( createAvsiktstyp() )
                .yrkandeDatum( ZonedDateTime.now() )
                .avserErbjudande( createErbjudande() )
                .hanterasIHandlaggningar( Collections.emptyList() )
                .rollerIYrkandet( List.of( createRollIYrkande(), createRollPersonenYrkandetAvser() ) )
                .yrkandeStatus( createYrkandestatus() )
                .from( ZonedDateTime.now() )
                .tom( ZonedDateTime.now().plusDays( 1 ) )
                .build();
    }
}
