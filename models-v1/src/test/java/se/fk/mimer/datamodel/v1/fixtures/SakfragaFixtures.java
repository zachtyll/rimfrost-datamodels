package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfragetyper;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfragetypkategori;
import se.fk.mimer.datamodel.v1.referensdata.forman.AnknytandeSakfragetyper;
import se.fk.mimer.datamodel.v1.referensdata.forman.BerattigadeSakfragetyper;
import se.fk.mimer.datamodel.v1.referensdata.forman.Sakfragetypkategorier;

import java.util.List;
import java.util.UUID;

public class SakfragaFixtures
{
    public SakfragaFixtures()
    {
    }

    public static Sakfragetyper createSakfragetyp()
    {
        return Sakfragetyper.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .anknytandeSakfraga( AnknytandeSakfragetyper.BIDRAGSSPARR )
                .rattenTillSakfraga( BerattigadeSakfragetyper.SJUKPENNING )
                .build();
    }


    public static Sakfragetypkategori createSakfragetypkategori()
    {
        return Sakfragetypkategori.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .sakfragetypkategori( Sakfragetypkategorier.DAGERSATTNING )
                .build();
    }

    public static Sakfraga createSakfraga()
    {
        return Sakfraga.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .beskrivning( "Testsakfråga" )
                .ingarIFormaner( List.of( FormanFixtures.createForman() ) )
                .sakfragetyper( createSakfragetyp() )
                .sakfragetypkategori( createSakfragetypkategori() )
                .build();
    }
}
