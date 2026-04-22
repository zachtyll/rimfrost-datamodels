package se.fk.mimer.codec.v1.registry;

import se.fk.mimer.codec.v1.config.CodecConfig;
import se.fk.mimer.datamodel.v1.beslut.Beslut;
import se.fk.mimer.datamodel.v1.beslut.delgivning.Delgivning;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.lagrum.Lagrum;
import se.fk.mimer.datamodel.v1.person.EnskildNaringsidkare;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;
import se.fk.mimer.datamodel.v1.person.JuridiskPerson;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.person.foretradare.Funktionar;
import se.fk.mimer.datamodel.v1.person.foretradare.Ombud;
import se.fk.mimer.datamodel.v1.produceratresultat.*;
import se.fk.mimer.datamodel.v1.produceratresultat.bedomdinkomst.BedomdInkomst;
import se.fk.mimer.datamodel.v1.produceratresultat.bedomdkostnad.BedomdKostnad;
import se.fk.mimer.datamodel.v1.produceratresultat.bidragssparr.Bidragssparr;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.Ersattning;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.beloppstyp.BeloppstypBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.berakningsgrund.BerakningsgrundBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.produceratresultat.intyg.*;
import se.fk.mimer.datamodel.v1.produceratresultat.krav.Krav;
import se.fk.mimer.datamodel.v1.produceratresultat.periodtillampliglagstiftning.PeriodTillampligLagstiftning;
import se.fk.mimer.datamodel.v1.produceratresultat.svensksocialforsakringsperiod.SvenskSocialforsakringsperiod;
import se.fk.mimer.datamodel.v1.produkt.erbjudande.Erbjudande;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.util.Map;

import static java.util.Map.entry;

public final class CodecRegistries
{
    private CodecRegistries() {}

    public static VariantRegistry createVariantRegistry()
    {

        Map<Class<?>, String> classToVariant = Map.ofEntries(
                entry( FysiskPerson.class, "fysiskPerson" ),
                entry( JuridiskPerson.class, "juridiskPerson" ),
                entry( Funktionar.class, "funktionar" ),
                entry( Ombud.class, "ombud" ),
                entry( BedomdInkomst.class, "bedomdInkomst" ),
                entry( BedomdKostnad.class, "bedomdKostnad" ),
                entry( Bidragssparr.class, "bidragssparr" ),
                entry( Ersattning.class, "ersattning" ),
                entry( Intyg.class, "intyg" ),
                entry( EUKort.class, "euKort" ),
                entry( Forhandstillstand.class, "forhandstillstand" ),
                entry( IntygOmTillampligLagstiftning.class, "intygOmTillampligLagstiftning" ),
                entry( IVIntyg.class, "iviIntyg" ),
                entry( Utforare.class, "utforare" ),
                entry( Krav.class, "krav" ),
                entry( PeriodTillampligLagstiftning.class, "periodTillampligLagstiftning" ),
                entry( SvenskSocialforsakringsperiod.class, "svenskSocialforsakringsperiod" ),
                entry( Bedomdarbetsformaga.class, "bedomdarbetsformaga" ),
                entry( KarenstidEnskildNaringsidkare.class, "karenstidEnskildNaringsidkare" ),
                entry( RattenTillPeriod.class, "rattenTillPeriod" )
        );

        Map<String, Class<?>> variantToClass = Map.ofEntries(
                entry( "fysiskPerson", FysiskPerson.class ),
                entry( "juridiskPerson", JuridiskPerson.class ),
                entry( "funktionar", Funktionar.class ),
                entry( "ombud", Ombud.class ),
                entry( "bedomdInkomst", BedomdInkomst.class ),
                entry( "bedomdKostnad", BedomdKostnad.class ),
                entry( "bidragssparr", Bidragssparr.class ),
                entry( "ersattning", Ersattning.class ),
                entry( "intyg", Intyg.class ),
                entry( "euKort", EUKort.class ),
                entry( "forhandstillstand", Forhandstillstand.class ),
                entry( "intygOmTillampligLagstiftning", IntygOmTillampligLagstiftning.class ),
                entry( "iviIntyg", IVIntyg.class ),
                entry( "utforare", Utforare.class ),
                entry( "krav", Krav.class ),
                entry( "periodTillampligLagstiftning", PeriodTillampligLagstiftning.class ),
                entry( "svenskSocialforsakringsperiod", SvenskSocialforsakringsperiod.class ),
                entry( "bedomdarbetsformaga", Bedomdarbetsformaga.class ),
                entry( "karenstidEnskildNaringsidkare", KarenstidEnskildNaringsidkare.class ),
                entry( "rattenTillPeriod", RattenTillPeriod.class )
        );


        return new DefaultVariantRegistry( classToVariant, variantToClass );
    }

    public static TypeRegistry createTypeRegistry()
    {
        String modelVersion = CodecConfig.MODEL_VERSION;

        Map<Class<?>, String> classToTypeToken = Map.ofEntries(
                entry(Yrkande.class, "Yrkande"),
                entry(Handlaggning.class, "Handlaggning"),
                entry(Delgivning.class, "Delgivning"),
                entry(Beslut.class, "Beslut"),
                entry(Lagrum.class, "Lagrum"),
                entry(Funktionar.class, "Funktionar"),
                entry(Ombud.class, "Ombud"),
                entry(EnskildNaringsidkare.class, "EnskildNaringsidkare"),
                entry(FysiskPerson.class, "FysiskPerson"),
                entry(JuridiskPerson.class, "JuridiskPerson"),
                entry(BedomdInkomst.class, "BedomdInkomst"),
                entry(BedomdKostnad.class, "BedomdKostnad"),
                entry(Bidragssparr.class, "Bidragssparr"),
                entry(BeloppstypBaseratPaErsattningstypEnligtLagrum.class, "BeloppstypBaseratPaErsattningstypEnligtLagrum"),
                entry(BerakningsgrundBaseratPaErsattningstypEnligtLagrum.class, "BerakningsgrundBaseratPaErsattningstypEnligtLagrum"),
                entry(ErsattningstypEnligtLagrum.class, "ErsattningstypEnligtLagrum"),
                entry(OmfattningBaseratPaErsattningstypEnligtLagrum.class, "OmfattningBaseratPaErsattningstypEnligtLagrum"),
                entry(Ersattning.class, "Ersattning"),
                entry(EUKort.class, "EUKort"),
                entry(Forhandstillstand.class, "Forhandstillstand"),
                entry(Intyg.class, "Intyg"),
                entry(IntygOmTillampligLagstiftning.class, "IntygOmTillampligLagstiftning"),
                entry(IVIntyg.class, "IVIntyg"),
                entry(UtlandsktForetag.class, "UtlandsktForetag"),
                entry(Krav.class, "Krav"),
                entry(PeriodTillampligLagstiftning.class, "PeriodTillampligLagstiftning"),
                entry(Bedomdarbetsformaga.class, "Bedomdarbetsformaga"),
                entry(KarenstidEnskildNaringsidkare.class, "KarenstidEnskildNaringsidkare"),
                entry(RattenTillPeriod.class, "RattenTillPeriod"),
                entry(Utforare.class, "Utforare"),
                entry(Erbjudande.class, "Erbjudande"),
                entry(ProduceratResultat.class, "ProduceratResultat"),
                entry(Person.class, "Person")
        );

        Map<String, Class<?>> typeTokenToClass = Map.ofEntries(
                entry("Yrkande", Yrkande.class),
                entry("Handlaggning", Handlaggning.class),
                entry("Delgivning", Delgivning.class),
                entry("Beslut", Beslut.class),
                entry("Lagrum", Lagrum.class),
                entry("Funktionar", Funktionar.class),
                entry("Ombud", Ombud.class),
                entry("EnskildNaringsidkare", EnskildNaringsidkare.class),
                entry("FysiskPerson", FysiskPerson.class),
                entry("JuridiskPerson", JuridiskPerson.class),
                entry("BedomdInkomst", BedomdInkomst.class),
                entry("BedomdKostnad", BedomdKostnad.class),
                entry("Bidragssparr", Bidragssparr.class),
                entry("BeloppstypBaseratPaErsattningstypEnligtLagrum", BeloppstypBaseratPaErsattningstypEnligtLagrum.class),
                entry("BerakningsgrundBaseratPaErsattningstypEnligtLagrum", BerakningsgrundBaseratPaErsattningstypEnligtLagrum.class),
                entry("ErsattningstypEnligtLagrum", ErsattningstypEnligtLagrum.class),
                entry("OmfattningBaseratPaErsattningstypEnligtLagrum", OmfattningBaseratPaErsattningstypEnligtLagrum.class),
                entry("Ersattning", Ersattning.class),
                entry("EUKort", EUKort.class),
                entry("Forhandstillstand", Forhandstillstand.class),
                entry("Intyg", Intyg.class),
                entry("IntygOmTillampligLagstiftning", IntygOmTillampligLagstiftning.class),
                entry("IVIntyg", IVIntyg.class),
                entry("UtlandsktForetag", UtlandsktForetag.class),
                entry("Krav", Krav.class),
                entry("PeriodTillampligLagstiftning", PeriodTillampligLagstiftning.class),
                entry("Bedomdarbetsformaga", Bedomdarbetsformaga.class),
                entry("KarenstidEnskildNaringsidkare", KarenstidEnskildNaringsidkare.class),
                entry("RattenTillPeriod", RattenTillPeriod.class),
                entry("Utforare", Utforare.class),
                entry("Erbjudande", Erbjudande.class),
                entry("ProduceratResultat", ProduceratResultat.class),
                entry("Person", Person.class)
        );

        return new DefaultTypeRegistry( modelVersion, classToTypeToken, typeTokenToClass );
    }
}
