package se.fk.mimer.codec.v1.fixtures;

import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudande;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.referensdata.forman.erbjudande.Erbjudandenamn;

import java.util.List;
import java.util.UUID;

public class ErbjudandeFixtures
{
    public ErbjudandeFixtures()
    {
    }

    public static Erbjudandetyp createErbjudandetyp()
    {
        return Erbjudandetyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .erbjudandenamn( Erbjudandenamn.ANSOKA_OM_SJUKPENNING )
                .build();
    }

    public static Erbjudande createErbjudande()
    {
        return Erbjudande.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .erbjudande( createErbjudandetyp() )
                .ingarIForman( List.of( FormanFixtures.createForman() ) )
                .beskrivning( "Testbeskrivning" )
                .build();
    }
}
