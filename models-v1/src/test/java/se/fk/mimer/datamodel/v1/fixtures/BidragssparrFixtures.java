package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.referensdata.sakfraga.medanknytningtillformaner.Bidragssparrgrund;
import se.fk.mimer.datamodel.v1.referensdata.sakfraga.medanknytningtillformaner.IngenSparrAnledning;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr.bidragssparr.AnledningIngenBidragssparr;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr.bidragssparr.Bidragssparr;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr.bidragssparr.GrundForBidragssparrutredning;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createPerson;
import static se.fk.mimer.datamodel.v1.fixtures.SakfragaFixtures.createSakfraga;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createYrkande;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createYrkandestatus;

public class BidragssparrFixtures
{
    public BidragssparrFixtures()
    {
    }

    public static AnledningIngenBidragssparr createAnledningIngenBidragssparr()
    {
        return AnledningIngenBidragssparr.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .ingenSparrAnledning( IngenSparrAnledning.OSKALIGT_PGA_BARNETS_BASTA )
                .build();
    }

    public static GrundForBidragssparrutredning createGrundForBidragssparrutredning()
    {
        return GrundForBidragssparrutredning.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .bidragssparrgrund( Bidragssparrgrund.BROTTSMISSTANKE )
                .build();
    }

    public static Bidragssparr createBidragssparr()
    {
        return Bidragssparr.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .from( ZonedDateTime.now() )
                .tom( ZonedDateTime.now().plusDays( 1 ) )
                .yrkandestatus( createYrkandestatus() )
                .avserPersoner( List.of( createPerson() ) )
                .godkandRegler( Collections.emptyList() )
                .avslagPaGrundAvRegler( Collections.emptyList() )
                .faststallsForYrkande( createYrkande() )
                .avserSakfraga( createSakfraga() )
                .formanssparr( UUID.randomUUID() )
                .grundForBidragssparrutredning( createGrundForBidragssparrutredning() )
                .anledningIngenBidragssparr( createAnledningIngenBidragssparr() )
                .build();

    }
}
