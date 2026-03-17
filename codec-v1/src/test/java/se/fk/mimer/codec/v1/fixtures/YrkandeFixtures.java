package se.fk.mimer.codec.v1.fixtures;

import se.fk.mimer.codec.v1.util.TestValues;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.beslut.Beslut;
import se.fk.mimer.datamodel.v1.referensdata.beslut.BeslutandeOrganisation;
import se.fk.mimer.datamodel.v1.beslut.delgivning.Delgivning;
import se.fk.mimer.datamodel.v1.referensdata.beslut.Delgivningstyp;
import se.fk.mimer.datamodel.v1.lagrum.Lagrum;
import se.fk.mimer.datamodel.v1.person.EnskildNaringsidkare;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.Periodisering;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.Beloppstyp;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.BeloppstypKategori;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.produceratresultat.krav.Krav;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.Kravtyp;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Produktnamn;
import se.fk.mimer.datamodel.v1.produkt.Produkt;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Produktroller;
import se.fk.mimer.datamodel.v1.produkt.RollIProdukt;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.Avsikt;
import se.fk.mimer.datamodel.v1.yrkande.RollIYrkande;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.RollerIYrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.util.List;
import java.util.Map;

public class YrkandeFixtures
{
    private YrkandeFixtures()
    {
    }

    private static Period createPeriod()
    {
        return Period.builder()
                .from( TestValues.now() )
                .tom( TestValues.now() )
                .build();
    }

    private static Lagrum createLagrum()
    {
        return Lagrum.builder()
                .id( TestValues.uuid() )
                .forfattning( "forfattning" )
                .kapitel( "kapitel" )
                .paragraf( "paragraf" )
                .stycke( "stycke" )
                .punkt( "punkt" )
                .giltighetstid( createPeriod() )
                .build();
    }

    private static Beslut createBeslut()
    {
        return Beslut.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .avserKundbehov( TestValues.uuid() )
                .beslutsdatum( TestValues.now() )
                .beslutsfattareId( "1234" )
                .beslutEnligtLagrum( createLagrum() )
                .beslutandeOrganisation( BeslutandeOrganisation.FORSAKRINGSKASSAN )
                .avslagsAnledning( "Handläggningsfel" )
                .build();
    }

    private static Delgivning createDelgivning()
    {
        return Delgivning.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .delgivningsdatum( TestValues.now() )
                .delgivningstyp( Delgivningstyp.VANLIG )
                .beslutId( TestValues.uuid() )
                .build();
    }

    private static se.fk.mimer.datamodel.v1.produkt.Erbjudande createErbjudande()
    {
        return se.fk.mimer.datamodel.v1.produkt.Erbjudande.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .erbjudandeNamn( "erbjudandeNamn" )
                .produktnamn( Produktnamn.UNDERHALLSSTOD )
                .erbjudandetyp( Erbjudandetyp.BARNINKOMSTAVDRAG )
                .build();
    }

    private static Produkt createProdukt()
    {
        return Produkt.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .produktnamn( Produktnamn.UNDERHALLSSTOD )
                .roller( new RollIProdukt[]{
                        createRolliProdukt()
                } )
                .erbjudande( Map.of( TestValues.uuid(), createErbjudande() ) )
                .build();
    }

    private static RollIProdukt createRolliProdukt()
    {
        return RollIProdukt.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .kundid( "kundID" )
                .produktid( TestValues.uuid() )
                .roll( Produktroller.PRODUKTAGARE )
                .build();
    }

    private static EnskildNaringsidkare createEnskildNaringsIdkare()
    {
        return EnskildNaringsidkare.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .build();
    }

    private static RollIYrkande createRolliYrkande()
    {
        return RollIYrkande.builder()
                .kundid( "kundID" )
                .roll( RollerIYrkande.MAKA )
                .yrkande( true )
                .build();
    }

    private static FysiskPerson createFysiskPerson()
    {
        return FysiskPerson.builder()
                .id( TestValues.uuid() )
                .kundid( "kundID" )
                .revision( TestValues.revision() )
                .personnummer( "199203459456" )
                .rollIProdukt( createRolliProdukt() )
                .enskildNaringsidkare( createEnskildNaringsIdkare() )
                .rollIKundbehov( Map.of( TestValues.uuid(), createRolliYrkande() ) )
                .build();
    }

    private static OmfattningBaseratPaErsattningstypEnligtLagrum createOmfattningBaseratPaErsattningstypEnligtLagrum()
    {
        return OmfattningBaseratPaErsattningstypEnligtLagrum.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .procentAvErsattning( 2.3 )
                .build();
    }

    private static Krav createKrav()
    {
        return Krav.builder()
                .id( TestValues.uuid() )
                .faststallsForKundbehov( TestValues.uuid() )
                .revision( TestValues.revision() )
                .avserPerson( createFysiskPerson() )
                .giltighetsperiod( createPeriod() )
                .typ( "typ" )
                .status( "status" )
                .beloppstypKategori( BeloppstypKategori.AS )
                .beloppstyp( Beloppstyp.AS_GARANTIBELOPP )
                .kravtyp( Kravtyp.BETALNINGSBELOPP_FOR_UNDERHALLSSTOD )
                .periodisering( Periodisering.AR )
                .omfattning( createOmfattningBaseratPaErsattningstypEnligtLagrum() )
                .build();
    }

    public static Yrkande createYrkande()
    {
        return Yrkande.builder()
                .id( TestValues.uuid() )
                .revision( TestValues.revision() )
                .avsikt( Avsikt.NY )
                .andringsorsak( "Omprövning" )
                .yrkandeDatum( TestValues.now() )
                .period( createPeriod() )
                .beslut( createBeslut() )
                .delgivning( createDelgivning() )
                .avserErbjudande( createErbjudande() )
                .produkt( createProdukt() )
                .personer( List.of( createFysiskPerson() ) )
                .produceradeResultat( List.of( createKrav() ) )
                .build();
    }
}
