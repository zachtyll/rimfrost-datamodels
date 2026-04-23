package se.fk.mimer.codec.v1.fixtures;

import se.fk.mimer.datamodel.v1.person.adress.Folkbokforingsadress;

import java.util.UUID;

public class FolkbokforingsadressFixtures
{
    private FolkbokforingsadressFixtures()
    {
    }

    public static Folkbokforingsadress createFolkbokforingsadress()
    {
        return Folkbokforingsadress.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .careOf( "" )
                .utdelningsadress1( "Testvägen 1" )
                .postnummer( "12323" )
                .postort( "Testia" )
                .build();
    }
}
