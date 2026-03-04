package se.fk.mimer.codec.v2.registry;

import se.fk.mimer.codec.v2.config.CodecConfig;
import se.fk.mimer.datamodel.v2.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v2.person.FysiskPerson;
import se.fk.mimer.datamodel.v2.person.JuridiskPerson;
import se.fk.mimer.datamodel.v2.person.foretradare.Funktionar;
import se.fk.mimer.datamodel.v2.person.foretradare.Ombud;
import se.fk.mimer.datamodel.v2.produceratresultat.Bedomdarbetsformaga;
import se.fk.mimer.datamodel.v2.produceratresultat.KarenstidEnskildNaringsidkare;
import se.fk.mimer.datamodel.v2.produceratresultat.RattenTillPeriod;
import se.fk.mimer.datamodel.v2.produceratresultat.Utforare;
import se.fk.mimer.datamodel.v2.produceratresultat.bedomdinkomst.BedomdInkomst;
import se.fk.mimer.datamodel.v2.produceratresultat.bedomdkostnad.BedomdKostnad;
import se.fk.mimer.datamodel.v2.produceratresultat.bidragssparr.Bidragssparr;
import se.fk.mimer.datamodel.v2.produceratresultat.ersattning.Ersattning;
import se.fk.mimer.datamodel.v2.produceratresultat.intyg.EUKort;
import se.fk.mimer.datamodel.v2.produceratresultat.intyg.Forhandstillstand;
import se.fk.mimer.datamodel.v2.produceratresultat.intyg.IVIntyg;
import se.fk.mimer.datamodel.v2.produceratresultat.intyg.Intyg;
import se.fk.mimer.datamodel.v2.produceratresultat.intyg.IntygOmTillampligLagstiftning;
import se.fk.mimer.datamodel.v2.produceratresultat.krav.Krav;
import se.fk.mimer.datamodel.v2.produceratresultat.periodtillampliglagstiftning.PeriodTillampligLagstiftning;
import se.fk.mimer.datamodel.v2.produceratresultat.svensksocialforsakringsperiod.SvenskSocialforsakringsperiod;
import se.fk.mimer.datamodel.v2.yrkande.Yrkande;

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

        Map<Class<?>, String> classToTypeToken = Map.of(
                Yrkande.class, "Yrkande",
                Handlaggning.class, "Handlaggning"

        );

        Map<String, Class<?>> typeTokenToClass = Map.of(
                "Yrkande", Yrkande.class,
                "Handlaggning", Handlaggning.class
        );

        return new DefaultTypeRegistry( modelVersion, classToTypeToken, typeTokenToClass );
    }
}
