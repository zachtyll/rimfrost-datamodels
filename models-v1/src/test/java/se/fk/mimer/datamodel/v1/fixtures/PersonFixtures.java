package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;
import se.fk.mimer.datamodel.v1.person.Individ;
import se.fk.mimer.datamodel.v1.person.Persontyp;
import se.fk.mimer.datamodel.v1.person.adress.Folkbokforingsadress;
import se.fk.mimer.datamodel.v1.referensdata.IDTyper;

import java.util.UUID;

public class PersonFixtures
{
    public PersonFixtures()
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

    public static Individ createIndivid()
    {
        return Individ.builder()
                .id( UUID.randomUUID() )
                .build();
    }

    public static FysiskPerson createFysiskPerson()
    {
        return FysiskPerson.builder()
                .id( UUID.randomUUID() )
                .efternamn( "Testsson" )
                .fornamn( "Test" )
                .kon( "Man" )
                .adress( createFolkbokforingsadress() )
                .build();
    }

    public static IDTyp createIdTyp()
    {
        return IDTyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .idTyp( IDTyper.PERSONNUMMER )
                .varde( "19420101-4444" )
                .build();
    }

    public static Persontyp createPersontyp()
    {
        return Persontyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .idTyp( createIdTyp() )
                .arEnIndivid( createIndivid() )
                .fysiskPerson( createFysiskPerson() )
                .build();
    }
}
