package se.fk.mimer.codec.v1.registry;

import se.fk.mimer.codec.v1.config.CodecConfig;
import se.fk.mimer.datamodel.v1.Berakningsgrunder;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.Periodisering;
import se.fk.mimer.datamodel.v1.Verksamhetslogiktyper;
import se.fk.mimer.datamodel.v1.anvandare.Anvandare;
import se.fk.mimer.datamodel.v1.anvandare.ErbjudanDatamangdsgrupper;
import se.fk.mimer.datamodel.v1.anvandare.ErbjudnaDatamangder;
import se.fk.mimer.datamodel.v1.anvandare.Team;
import se.fk.mimer.datamodel.v1.anvandare.Teamtyp;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Behorighetsgrupper;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Behorighetsroller;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Yrkesroll;
import se.fk.mimer.datamodel.v1.forman.Forman;
import se.fk.mimer.datamodel.v1.forman.Formanstyp;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudande;
import se.fk.mimer.datamodel.v1.forman.erbjudande.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfragetyper;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfragetypkategori;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggningsspecifikation;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Basuppgift;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.FSSAInformationstyp;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Informationsobjekt;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Tjansteanteckning;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Uppgift;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Uppgiftsspecifikation;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Uppgiftsstatustyp;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.processbeskrivning.AbstraktProcessbeskrivning;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.regelutfall.Regelutfallstyp;
import se.fk.mimer.datamodel.v1.inkomst.Inkomst;
import se.fk.mimer.datamodel.v1.inkomst.InkomstAvKapital;
import se.fk.mimer.datamodel.v1.inkomst.InkomstAvNaringsverksamhet;
import se.fk.mimer.datamodel.v1.inkomst.InkomstAvTjanst;
import se.fk.mimer.datamodel.v1.inkomst.anstallning.Anstallning;
import se.fk.mimer.datamodel.v1.inkomst.anstallning.AvtaladLon;
import se.fk.mimer.datamodel.v1.inkomst.anstallning.AvtaladLoneforman;
import se.fk.mimer.datamodel.v1.inkomst.anstallning.AvtaladLoneformanstyp;
import se.fk.mimer.datamodel.v1.inkomst.anstallning.Bisyssla;
import se.fk.mimer.datamodel.v1.inkomst.ersattning.Ersattningsrad;
import se.fk.mimer.datamodel.v1.inkomst.ersattning.ErsattningsradTyp;
import se.fk.mimer.datamodel.v1.inkomst.ersattning.SpecificeradErsattningsperiod;
import se.fk.mimer.datamodel.v1.inkomst.ersattning.UtbetaldAllmanErsattning;
import se.fk.mimer.datamodel.v1.inkomst.ersattning.UtbetaldAllmanErsattningsrad;
import se.fk.mimer.datamodel.v1.organisation.JuridiskFormkod;
import se.fk.mimer.datamodel.v1.organisation.Organisation;
import se.fk.mimer.datamodel.v1.organisation.OrganisationsForm;
import se.fk.mimer.datamodel.v1.organisation.OrganisationsIdentitet;
import se.fk.mimer.datamodel.v1.organisation.Organisationsenhet;
import se.fk.mimer.datamodel.v1.person.Funktionar;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;
import se.fk.mimer.datamodel.v1.person.Individ;
import se.fk.mimer.datamodel.v1.person.Persontyp;
import se.fk.mimer.datamodel.v1.person.adress.Adress;
import se.fk.mimer.datamodel.v1.person.adress.Folkbokforingsadress;
import se.fk.mimer.datamodel.v1.regel.Lagrum;
import se.fk.mimer.datamodel.v1.regel.ParagrafArtikel;
import se.fk.mimer.datamodel.v1.regel.Punkt;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.regel.Stycke;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.SakfragaStallningstagande;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.SakfragaIHandlaggningen;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.Betalningsbelopp;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.SFBInkomsttyp;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.SjukpenninggrundandeInkomst;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.bidragssparr.AnledningIngenBidragssparr;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.bidragssparr.Bidragssparr;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.bidragssparr.GrundForBidragssparrutredning;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill.Beloppstyper;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill.RattenTillPeriod;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill.ersattning.Ersattning;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.Stallningstagande;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.Stallningstagandetyp;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagandeIHandlaggningen;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenBosattningOchEllerArbeteISverige;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenInkomst;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenKostnad;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagnaInkomsttyper;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagnaKostnadstyper;
import se.fk.mimer.datamodel.v1.tjanstgoringitotalforsvaret.PlikttypForTjanstgoringITotalforsvaret;
import se.fk.mimer.datamodel.v1.tjanstgoringitotalforsvaret.TjanstgoringITotalforsvaret;
import se.fk.mimer.datamodel.v1.tjanstgoringitotalforsvaret.TjanstgoringsstatusForTjanstgoringITotalforsvaret;
import se.fk.mimer.datamodel.v1.tjanstgoringitotalforsvaret.TjanstgoringstypITotalforsvaret;
import se.fk.mimer.datamodel.v1.yrkande.Avsiktstyp;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Avslutstyp;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslut;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutsrad;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutstyp;
import se.fk.mimer.datamodel.v1.yrkande.beslut.Beslutsutfallstyp;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIResultatet;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;
import se.fk.mimer.datamodel.v1.yrkande.roller.Yrkanderoll;

import java.util.Map;

import static java.util.Map.entry;

public final class CodecRegistries
{
    private CodecRegistries() {}

    public static VariantRegistry createVariantRegistry()
    {

        Map<Class<?>, String> classToVariant = Map.ofEntries(
                entry( Bisyssla.class, "Bisyssla" ),
                entry( InkomstAvKapital.class, "InkomstAvKapital" ),
                entry( InkomstAvNaringsverksamhet.class, "InkonstAvNaringsverksamhet" ),
                entry( InkomstAvTjanst.class, "InkomstAvTjanst" ),
                entry( Folkbokforingsadress.class, "Folkbokforingsadress" ),
                entry( Tjansteanteckning.class, "Tjansteanteckning" ),
                entry( Uppgift.class, "Uppgift" ),
                entry( ParagrafArtikel.class, "ParagrafArtikel" ),
                entry( Stycke.class, "Stycke" ),
                entry( Punkt.class, "Punkt" ),
                entry( SakfragaIHandlaggningen.class, "SakfragaIHandlaggningen" ),
                entry( RattenTillPeriod.class, "RattenTillPeriod" ),
                entry( Ersattning.class, "Ersattning" ),
                entry( Bidragssparr.class, "Bidragssparr" ),
                entry( Betalningsbelopp.class, "Betalningsbelopp" ),
                entry( SjukpenninggrundandeInkomst.class, "SjukpenninggrundandeInkomst" ),
                entry( StallningstagandeIHandlaggningen.class, "StallningstagandeIHandlaggningen" ),
                entry( StallningstagenKostnad.class, "StallningstagenKostnad" ),
                entry( StallningstagenInkomst.class, "StallningstagenInkomst" ),
                entry( StallningstagenBosattningOchEllerArbeteISverige.class,
                        "StallningstagenBosattningEllerArbeteISverige" ),
                entry( StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner.class, "StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner" )
        );

        Map<String, Class<?>> variantToClass = Map.ofEntries(
                entry( "Bisyssla", Bisyssla.class ),
                entry( "InkomstAvKapital", InkomstAvKapital.class ),
                entry( "InkonstAvNaringsverksamhet", InkomstAvNaringsverksamhet.class ),
                entry( "InkomstAvTjanst", InkomstAvTjanst.class ),
                entry( "Folkbokforingsadress", Folkbokforingsadress.class ),
                entry( "Tjansteanteckning", Tjansteanteckning.class ),
                entry( "Uppgift", Uppgift.class ),
                entry( "ParagrafArtikel", ParagrafArtikel.class ),
                entry( "Stycke", Stycke.class ),
                entry( "Punkt", Punkt.class ),
                entry( "SakfragaIHandlaggningen", SakfragaIHandlaggningen.class ),
                entry( "RattenTillPeriod", RattenTillPeriod.class ),
                entry( "Ersattning", Ersattning.class ),
                entry( "Bidragssparr", Bidragssparr.class ),
                entry( "Betalningsbelopp", Betalningsbelopp.class ),
                entry( "SjukpenninggrundandeInkomst", SjukpenninggrundandeInkomst.class ),
                entry( "StallningstagandeIHandlaggningen", StallningstagandeIHandlaggningen.class ),
                entry( "StallningstagenKostnad", StallningstagenKostnad.class ),
                entry( "StallningstagenInkomst", StallningstagenInkomst.class ),
                entry( "StallningstagenBosattningEllerArbeteISverige",
                        StallningstagenBosattningOchEllerArbeteISverige.class ),
                entry( "StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner",
                        StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner.class )
        );


        return new DefaultVariantRegistry( classToVariant, variantToClass );
    }

    public static TypeRegistry createTypeRegistry()
    {
        String modelVersion = CodecConfig.MODEL_VERSION;

        Map<Class<?>, String> classToTypeToken = Map.ofEntries(
                entry( Sakfragetypkategori.class, "Sakfragetypkategori" ),
                entry( Individ.class, "Individ" ),
                entry( Anvandare.class, "Anvandare" ),
                entry( ErbjudanDatamangdsgrupper.class, "ErbjudanDatamangdsgrupper" ),
                entry( ErbjudnaDatamangder.class, "ErbjudnaDatamangder" ),
                entry( Team.class, "Team" ),
                entry( Teamtyp.class, "Teamtyp" ),
                entry( Behorighetsgrupper.class, "Behorighetsgrupper" ),
                entry( Behorighetsroller.class, "Behorighetsroller" ),
                entry( Yrkesroll.class, "Yrkesroll" ),
                entry( Forman.class, "Forman" ),
                entry( Formanstyp.class, "Formanstyp" ),
                entry( Erbjudande.class, "Erbjudande" ),
                entry( Erbjudandetyp.class, "Erbjudandetyp" ),
                entry( Sakfraga.class, "Sakfraga" ),
                entry( Sakfragetyper.class, "Sakfragetyper" ),
                entry( Handlaggning.class, "Handlaggning" ),
                entry( Handlaggningsspecifikation.class, "Handlaggningsspecifikation" ),
                entry( AbstraktProcessbeskrivning.class, "AbstraktProcessbeskrivning" ),
                entry( Regelutfallstyp.class, "Regelutfallstyp" ),
                entry( Basuppgift.class, "Basuppgift" ),
                entry( FSSAInformationstyp.class, "FssaInformationstyp" ),
                entry( Informationsobjekt.class, "Informationsobjekt" ),
                entry( Tjansteanteckning.class, "Tjansteanteckning" ),
                entry( Uppgift.class, "Uppgift" ),
                entry( Uppgiftsspecifikation.class, "Uppgiftsspecifikation" ),
                entry( Uppgiftsstatustyp.class, "Uppgiftsstatustyper" ),
                entry( Anstallning.class, "Anstallning" ),
                entry( AvtaladLon.class, "AvataladLon" ),
                entry( AvtaladLoneforman.class, "AvtaladLoneforman" ),
                entry( AvtaladLoneformanstyp.class, "AvtaladLoneformanstyp" ),
                entry( Bisyssla.class, "Bisyssla" ),
                entry( Ersattningsrad.class, "Ersattningsrad" ),
                entry( ErsattningsradTyp.class, "ErsaattningsradTyp" ),
                entry( SpecificeradErsattningsperiod.class, "SpecificeradErsattningsperiod" ),
                entry( UtbetaldAllmanErsattning.class, "UtbetaldAllmanErsattning" ),
                entry( UtbetaldAllmanErsattningsrad.class, "UtbetaldAllmanErsattningsrad" ),
                entry( Inkomst.class, "Inkomst" ),
                entry( InkomstAvKapital.class, "InkomstAvKapital" ),
                entry( InkomstAvNaringsverksamhet.class, "InkomstAvNaringsverksamhet" ),
                entry( InkomstAvTjanst.class, "InkomstAvTjanst" ),
                entry( JuridiskFormkod.class, "JuridiskFormkod" ),
                entry( Organisation.class, "Organisation" ),
                entry( Organisationsenhet.class, "Organisationsenhet" ),
                entry( OrganisationsForm.class, "OrganisationsForm" ),
                entry( OrganisationsIdentitet.class, "OrganisationsIdentitet" ),
                entry( Adress.class, "Adress" ),
                entry( Folkbokforingsadress.class, "Folkbokforingsadress" ),
                entry( Funktionar.class, "Funktionar" ),
                entry( FysiskPerson.class, "FysiskPerson" ),
                entry( Persontyp.class, "Persontyp" ),
                entry( Lagrum.class, "Lagrum" ),
                entry( ParagrafArtikel.class, "ParagrafArtikel" ),
                entry( Punkt.class, "Punkt" ),
                entry( Regel.class, "Regel" ),
                entry( Stycke.class, "Stycke" ),
                entry( AnledningIngenBidragssparr.class, "AnledningIngenBidragssparr" ),
                entry( Bidragssparr.class, "Bidragssparr" ),
                entry( GrundForBidragssparrutredning.class, "GrundForBidragssparrutredning" ),
                entry( Betalningsbelopp.class, "Betalningsbelopp" ),
                entry( SFBInkomsttyp.class, "SfbInkomsttyp" ),
                entry( SjukpenninggrundandeInkomst.class, "SjukpenninggrundandeInkomst" ),
                entry( Ersattning.class, "Ersattning" ),
                entry( Beloppstyper.class, "Beloppstyper" ),
                entry( RattenTillPeriod.class, "RattenTillPeriod" ),
                entry( SakfragaIHandlaggningen.class, "SakfragaIHandlaggningen" ),
                entry( Stallningstagande.class, "Stallningstagande" ),
                entry( Stallningstagandetyp.class, "Stallningstagandetyp" ),
                entry( StallningstagandeIHandlaggningen.class, "StallningstagandeIHandlaggningen" ),
                entry( StallningstagenBosattningOchEllerArbeteISverige.class,
                        "StallningstagenBosattningEllerArbeteISverige" ),
                entry( StallningstagenInkomst.class, "StallningstagenInkomst" ),
                entry( StallningstagenKostnad.class, "StallningstagenKostnad" ),
                entry( StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner.class, "StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner" ),
                entry( StallningstagnaInkomsttyper.class, "StallningstagnaInkomsttyper" ),
                entry( StallningstagnaKostnadstyper.class, "StallningstagnaKostnadstyper" ),
                entry( SakfragaStallningstagande.class, "SakfragaStallningstagande" ),
                entry( PlikttypForTjanstgoringITotalforsvaret.class, "PlikttypForTjanstgoringITotalforsvaret" ),
                entry( TjanstgoringITotalforsvaret.class, "TjanstgoringITotalforsvaret" ),
                entry( TjanstgoringsstatusForTjanstgoringITotalforsvaret.class,
                        "TjanstgoringsstatusForTjanstgoringITotalforsvaret" ),
                entry( TjanstgoringstypITotalforsvaret.class, "TjanstgoringstypITotalforsvaret" ),
                entry( Yrkande.class, "Yrkande" ),
                entry( Avslutstyp.class, "Avslutstyp" ),
                entry( Beslut.class, "Beslut" ),
                entry( Beslutsrad.class, "Beslutsrad" ),
                entry( Beslutstyp.class, "Beslutstyp" ),
                entry( Beslutsutfallstyp.class, "Beslutsutfallstyp" ),
                entry( Yrkanderoll.class, "Yrkanderoll" ),
                entry( RollIYrkande.class, "RollIYrkande" ),
                entry( RollIResultatet.class, "RollIResultatet" ),
                entry( Avsiktstyp.class, "Avsiktstyp" ),
                entry( Yrkandestatus.class, "Yrkandestatus" ),
                entry( Berakningsgrunder.class, "Berakningsgrunder" ),
                entry( IDTyp.class, "IDTyp" ),
                entry( Periodisering.class, "Periodisering" ),
                entry( Verksamhetslogiktyper.class, "Verksamhetslogiktyper" )
        );

        Map<String, Class<?>> typeTokenToClass = Map.ofEntries(
                entry( "Sakfragetypkategori", Sakfragetypkategori.class ),
                entry( "Individ", Individ.class ),
                entry( "Anvandare", Anvandare.class ),
                entry( "SakfragaStallningstagande", SakfragaStallningstagande.class ),
                entry( "SakfragaIHandlaggningen", SakfragaIHandlaggningen.class ),
                entry( "ErbjudanDatamangdsgrupper", ErbjudanDatamangdsgrupper.class ),
                entry( "ErbjudnaDatamangder", ErbjudnaDatamangder.class ),
                entry( "Team", Team.class ),
                entry( "Teamtyp", Teamtyp.class ),
                entry( "Behorighetsgrupper", Behorighetsgrupper.class ),
                entry( "Behorighetsroller", Behorighetsroller.class ),
                entry( "Yrkesroll", Yrkesroll.class ),
                entry( "Forman", Forman.class ),
                entry( "Formanstyp", Formanstyp.class ),
                entry( "Erbjudande", Erbjudande.class ),
                entry( "Erbjudandetyp", Erbjudandetyp.class ),
                entry( "Sakfraga", Sakfraga.class ),
                entry( "Sakfragetyper", Sakfragetyper.class ),
                entry( "Handlaggning", Handlaggning.class ),
                entry( "Handlaggningsspecifikation", Handlaggningsspecifikation.class ),
                entry( "AbstraktProcessbeskrivning", AbstraktProcessbeskrivning.class ),
                entry( "Regelutfallstyp", Regelutfallstyp.class ),
                entry( "Basuppgift", Basuppgift.class ),
                entry( "FssaInformationstyp", FSSAInformationstyp.class ),
                entry( "Informationsobjekt", Informationsobjekt.class ),
                entry( "Tjansteanteckning", Tjansteanteckning.class ),
                entry( "Uppgift", Uppgift.class ),
                entry( "Uppgiftsspecifikation", Uppgiftsspecifikation.class ),
                entry( "Uppgiftsstatustyper", Uppgiftsstatustyp.class ),
                entry( "Anstallning", Anstallning.class ),
                entry( "AvataladLon", AvtaladLon.class ),
                entry( "AvtaladLoneforman", AvtaladLoneforman.class ),
                entry( "AvtaladLoneformanstyp", AvtaladLoneformanstyp.class ),
                entry( "Bisyssla", Bisyssla.class ),
                entry( "Ersattningsrad", Ersattningsrad.class ),
                entry( "ErsaattningsradTyp", ErsattningsradTyp.class ),
                entry( "SpecificeradErsattningsperiod", SpecificeradErsattningsperiod.class ),
                entry( "UtbetaldAllmanErsattning", UtbetaldAllmanErsattning.class ),
                entry( "UtbetaldAllmanErsattningsrad", UtbetaldAllmanErsattningsrad.class ),
                entry( "Inkomst", Inkomst.class ),
                entry( "InkomstAvKapital", InkomstAvKapital.class ),
                entry( "InkomstAvNaringsverksamhet", InkomstAvNaringsverksamhet.class ),
                entry( "InkomstAvTjanst", InkomstAvTjanst.class ),
                entry( "JuridiskFormkod", JuridiskFormkod.class ),
                entry( "Organisation", Organisation.class ),
                entry( "Organisationsenhet", Organisationsenhet.class ),
                entry( "OrganisationsForm", OrganisationsForm.class ),
                entry( "OrganisationsIdentitet", OrganisationsIdentitet.class ),
                entry( "Adress", Adress.class ),
                entry( "Folkbokforingsadress", Folkbokforingsadress.class ),
                entry( "Funktionar", Funktionar.class ),
                entry( "FysiskPerson", FysiskPerson.class ),
                entry( "Persontyp", Persontyp.class ),
                entry( "Lagrum", Lagrum.class ),
                entry( "ParagrafArtikel", ParagrafArtikel.class ),
                entry( "Punkt", Punkt.class ),
                entry( "Regel", Regel.class ),
                entry( "Stycke", Stycke.class ),
                entry( "AnledningIngenBidragssparr", AnledningIngenBidragssparr.class ),
                entry( "Bidragssparr", Bidragssparr.class ),
                entry( "GrundForBidragssparrutredning", GrundForBidragssparrutredning.class ),
                entry( "Betalningsbelopp", Betalningsbelopp.class ),
                entry( "SfbInkomsttyp", SFBInkomsttyp.class ),
                entry( "SjukpenninggrundandeInkomst", SjukpenninggrundandeInkomst.class ),
                entry( "Ersattning", Ersattning.class ),
                entry( "Beloppstyper", Beloppstyper.class ),
                entry( "RattenTillPeriod", RattenTillPeriod.class ),
                entry( "Stallningstagande", Stallningstagande.class ),
                entry( "Stallningstagandetyp", Stallningstagandetyp.class ),
                entry( "StallningstagandeIHandlaggningen", StallningstagandeIHandlaggningen.class ),
                entry( "StallningstagenBosattningEllerArbeteISverige",
                        StallningstagenBosattningOchEllerArbeteISverige.class ),
                entry( "StallningstagenInkomst", StallningstagenInkomst.class ),
                entry( "StallningstagenKostnad", StallningstagenKostnad.class ),
                entry( "StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner",
                        StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner.class ),
                entry( "StallningstagnaInkomsttyper", StallningstagnaInkomsttyper.class ),
                entry( "StallningstagnaKostnadstyper", StallningstagnaKostnadstyper.class ),
                entry( "PlikttypForTjanstgoringITotalforsvaret", PlikttypForTjanstgoringITotalforsvaret.class ),
                entry( "TjanstgoringITotalforsvaret", TjanstgoringITotalforsvaret.class ),
                entry( "TjanstgoringsstatusForTjanstgoringITotalforsvaret",
                        TjanstgoringsstatusForTjanstgoringITotalforsvaret.class ),
                entry( "TjanstgoringstypITotalforsvaret", TjanstgoringstypITotalforsvaret.class ),
                entry( "Yrkande", Yrkande.class ),
                entry( "Avslutstyp", Avslutstyp.class ),
                entry( "Beslut", Beslut.class ),
                entry( "Beslutsrad", Beslutsrad.class ),
                entry( "Beslutstyp", Beslutstyp.class ),
                entry( "Beslutsutfallstyp", Beslutsutfallstyp.class ),
                entry( "Yrkanderoll", Yrkanderoll.class ),
                entry( "RollIYrkande", RollIYrkande.class ),
                entry( "RollIResultatet", RollIResultatet.class ),
                entry( "Avsiktstyp", Avsiktstyp.class ),
                entry( "Yrkandestatus", Yrkandestatus.class ),
                entry( "Berakningsgrunder", Berakningsgrunder.class ),
                entry( "IDTyp", IDTyp.class ),
                entry( "Periodisering", Periodisering.class ),
                entry( "Verksamhetslogiktyper", Verksamhetslogiktyper.class )
        );
        return new DefaultTypeRegistry( modelVersion, classToTypeToken, typeTokenToClass );
    }
}
