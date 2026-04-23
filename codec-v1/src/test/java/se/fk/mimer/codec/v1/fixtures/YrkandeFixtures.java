package se.fk.mimer.codec.v1.fixtures;

import se.fk.mimer.codec.v1.util.TestValues;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfragetyper;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.person.adress.Folkbokforingsadress;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudande;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.forman.Formanstyp;
import se.fk.mimer.datamodel.v1.referensdata.IDTyper;
import se.fk.mimer.datamodel.v1.referensdata.forman.erbjudande.Erbjudandenamn;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.YrkandeStatus;
import se.fk.mimer.datamodel.v1.forman.Forman;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.Avsiktstyper;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.somgerrattentill.RattenTillPeriod;
import se.fk.mimer.datamodel.v1.yrkande.Avsiktstyp;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslut;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class YrkandeFixtures
{
    private YrkandeFixtures()
    {
    }

    private static Erbjudandetyp createErbjudandetyp()
    {
        return Erbjudandetyp.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .erbjudandenamn( Erbjudandenamn.ANSOKA_OM_FORALDRAPENNING )
                .build();
    }

    private static Formanstyp createFormanstyp()
    {
        return Formanstyp.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .formanstyp( se.fk.mimer.datamodel.v1.referensdata.forman.Formanstyp.GEMENSAM )
                .build();
    }

    private static Forman createForman()
    {
        return Forman.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .formanstyp( createFormanstyp() )
                .build();
    }

    private static Erbjudande createErbjudande()
    {
        return Erbjudande.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .erbjudande( createErbjudandetyp() )
                .ingarIForman( List.of( createForman() ) )
                .build();
    }

    private static RollIYrkande createRolliYrkande()
    {
        return RollIYrkande.builder()
                .individ( createIdTyp() )
                .avserPerson( createPerson() )
                .avserYrkande( createYrkande() )
                .build();
    }

    private static Folkbokforingsadress createFolkbokforingsadress()
    {
        return Folkbokforingsadress.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .careOf( "" )
                .utdelningsadress1( "Testvägen 1" )
                .postnummer( "12323" )
                .postort( "Testia" )
                .build();
    }

    private static FysiskPerson createFysiskPerson()
    {
        return FysiskPerson.builder()
                .id( "194101014243" )
                .efternamn( "Testsson" )
                .fornamn( "Test" )
                .kon( "Man" )
                .adress( createFolkbokforingsadress() )
                .build();
    }

    private static IDTyp createIdTyp()
    {
        return IDTyp.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .idTyp( IDTyper.PERSONNUMMER )
                .build();
    }

    private static Person createPerson()
    {
        return Person.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .idTyp( createIdTyp() )
                .fysiskPerson( createFysiskPerson() )
                .build();
    }

    public static Avsiktstyp createAvsiktstyp()
    {
        return Avsiktstyp.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .avsiktstyp( Avsiktstyper.NY )
                .build();
    }

    public static Yrkandestatus createYrkandestatus()
    {
        return Yrkandestatus.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .status( YrkandeStatus.YRKAT )
                .build();
    }

    public static Sakfragetyper createSakfragetyper()
    {
        return Sakfragetyper.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .build();
    }

    public static Sakfraga createSakfraga()
    {
        return Sakfraga.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .sakfragetyper( createSakfragetyper() )
                .ingarIFormaner( List.of( createForman() ) )
                .beskrivning( "Testbeskrivning" )
                .build();
    }

    public static RattenTillPeriod createRattenTillPeriod()
    {
        return RattenTillPeriod.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .from( TestValues.now() )
                .tom( TestValues.now().plusDays( 1 ) )
                .yrkandestatus( createYrkandestatus() )
                .avserPersoner( List.of( createPerson() ) )
                .faststallsForYrkande( createYrkande() )
                .avserSakfraga( createSakfraga() )
                .omfattningIProcent( 100.0 )
                .build();
    }

    public static Beslut createBeslut()
    {
        return Beslut.builder()
                .id( TestValues.uuid() )
                .version( TestValues.revision() )
                .beslutsDatum( TestValues.now() )
                .beslutsfattare( createIdTyp() )
                .build();
    }

    public static Yrkande createYrkande()
    {
        return Yrkande.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .avserBeslut( List.of( createBeslut() ) )
                .avserSakfragaStallningstaganden( Collections.emptyList() )
                .avsikt( createAvsiktstyp() )
                .yrkandeDatum( ZonedDateTime.now() )
                .avserErbjudande( createErbjudande() )
                .avserBeslut( Collections.emptyList() )
                .rollerIYrkandet( List.of() )
                .yrkandeStatus( createYrkandestatus() )
                .yrkandeFrom( ZonedDateTime.now() )
                .yrkandeTom( ZonedDateTime.now().plusDays( 1 ) )
                .hanterasIHandlaggningar( Collections.emptyList() )
                .build();
    }
}
