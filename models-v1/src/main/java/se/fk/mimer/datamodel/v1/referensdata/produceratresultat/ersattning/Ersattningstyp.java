package se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning;

import lombok.Getter;

public enum Ersattningstyp
{
    // FEA
    AKTIVITETSSTOD( LagrumForErsattningstyp.FEA ),
    BOSTADSERSATTNING( LagrumForErsattningstyp.FEA ),
    ETABLERINGSERSATTNING( LagrumForErsattningstyp.FEA ),
    ETABLERINGSTILLAGG( LagrumForErsattningstyp.FEA ),
    UTVECKLINGSERSATTNING( LagrumForErsattningstyp.FEA ),
    UTFYLLNADSBIDRAG( LagrumForErsattningstyp.FEA ),

    // SFB
    ADOPTIONSBIDRAG( LagrumForErsattningstyp.SFB ),
    AKTIVITETSERSATTNING( LagrumForErsattningstyp.SFB ),
    ARBETSRESOR( LagrumForErsattningstyp.SFB ),
    ARBETSSKADA_KOSTNADSERSATTNING( LagrumForErsattningstyp.SFB ),
    ARBETSSKADELIVRANTA( LagrumForErsattningstyp.SFB ),
    ARBETSSKAESJUKPENNING( LagrumForErsattningstyp.SFB ),
    ASSISTANSERSATTNING( LagrumForErsattningstyp.SFB ),
    BARNBIDRAG_ALLMANT( LagrumForErsattningstyp.SFB ),
    BARNBIDRAG_FORLANGT( LagrumForErsattningstyp.SFB ),
    BEGRAVNINGSHJALP( LagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_ARBETSGIVARE_FOR_KOP_AV_ARBETSPLATSINRIKTAT_REHABILITERINGSSTOD_FOR_ATERGANG_TILL_ARBETE( LagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_ARBETSHJALPMEDEL( LagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_ARBETSNARA_STOD( LagrumForErsattningstyp.SFB ),
    BILSTOD_ANPASSNINGSBIDRAG( LagrumForErsattningstyp.SFB ),
    BILSTOD_ANSKAFFNINGSBIDRAG( LagrumForErsattningstyp.SFB ),
    BILSTOD_GRUNDBIDRAG( LagrumForErsattningstyp.SFB ),
    BILSTOD_KORKORTSUTBILDNING( LagrumForErsattningstyp.SFB ),
    BILSTOD_TILLAGGSBIDRAG( LagrumForErsattningstyp.SFB ),
    BOENDETILLAGG( LagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_KOSTNADER_FOR_BOSTAD( LagrumForErsattningstyp.SFB ),
    SARSKILT_BIDRAG_FOR_HEMMAVARANDE_BARN( LagrumForErsattningstyp.SFB ),
    SARSKILT_BIDRAG_FOR_BARN_SOM_BOR_VAXELVIS( LagrumForErsattningstyp.SFB ),
    UMGANGESBIDRAG_TILL_DEN_SOM_PA_GRUND_AV_VARDNAD_ELLER_UMGANGE_TIDVIS_HAR_BARN_BOENDE_I_SITT_HEM( LagrumForErsattningstyp.SFB ),
    TILLAGGSBIDRAG_TILL_BARNFAMILJER( LagrumForErsattningstyp.SFB ),
    BOSTADSSTOD( LagrumForErsattningstyp.SFB ),
    BOSTADSTILLAGG( LagrumForErsattningstyp.SFB ),
    EFTERLEVNADSRANTA( LagrumForErsattningstyp.SFB ),
    ERSATTNING_FOR_ARBETSRESOR_ISTALLET_FOR_SJUKPENNING( LagrumForErsattningstyp.SFB ),
    ERSATTNING_FOR_HOGA_SJUKLONEKOSTNADER( LagrumForErsattningstyp.SFB ),
    FORALDRAPENNING( LagrumForErsattningstyp.SFB ),
    FORALDRAPENNING_I_SAMBAND_MED_BARNS_FODELSE( LagrumForErsattningstyp.SFB ),
    REHABILITERINGSPENNING_I_SARSKILDA_FALL( LagrumForErsattningstyp.SFB ),
    REHABILITERING( LagrumForErsattningstyp.SFB ),
    GRAVIDITETSPENNING( LagrumForErsattningstyp.SFB ),
    HANDIKAPPSERSATTNING( LagrumForErsattningstyp.SFB ),
    KIGSSKADEERSATTNING_TILL_SJOMAN( LagrumForErsattningstyp.SFB ),
    MERKOSTNADSERSATTNING( LagrumForErsattningstyp.SFB ),
    NARSAENDEPENNING( LagrumForErsattningstyp.SFB ),
    OMVARDNADSBIDRAG( LagrumForErsattningstyp.SFB ),
    REHABILITERINGSERSATTNING( LagrumForErsattningstyp.SFB ),
    REHABILITERINGSERSATTNING_SARSKILT_BIDRAG( LagrumForErsattningstyp.SFB ),
    SJUKPENNING( LagrumForErsattningstyp.SFB ),
    SJUKPENNING_I_SARSKILDA_FALL( LagrumForErsattningstyp.SFB ),
    SARSKILT_BIDRAG( LagrumForErsattningstyp.SFB ),
    SJUKERSATTNING( LagrumForErsattningstyp.SFB ),
    SMITTBARAREERSATTNING( LagrumForErsattningstyp.SFB ),
    ADOPTIONSPENNING( LagrumForErsattningstyp.SFB ),
    TILLFALLIG_FORALDRAPENNING( LagrumForErsattningstyp.SFB ),
    SJUKLON( LagrumForErsattningstyp.SFB ),
    FORLANGT_UNDERHALLSSTOD( LagrumForErsattningstyp.SFB ),
    BIDRAGSSKYLDIG( LagrumForErsattningstyp.SFB ),
    UNDERHALLSBIDRAG( LagrumForErsattningstyp.SFB ),
    UNDERHALLSSTOD( LagrumForErsattningstyp.SFB ),

    // LAG X
    DAGPENNING_TILL_TOTALFORSVARSPLIKTIGA( LagrumForErsattningstyp.LAGX ),
    BEGRAVNINGSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA( LagrumForErsattningstyp.LAGX ),
    FAMILJEPENNING_TILL_TOTALFORSVARSPLIKTIGA( LagrumForErsattningstyp.LAGX ),
    BOSTADSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA( LagrumForErsattningstyp.LAGX ),
    NARINGSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA( LagrumForErsattningstyp.LAGX ),

    // LAG Y
    ERSATTNING_FOR_ETABLERINGSJOBB( LagrumForErsattningstyp.LAGY );

    @Getter
    private final LagrumForErsattningstyp lagrumForErsattningstyp;

    Ersattningstyp( LagrumForErsattningstyp lagrum )
    {
        this.lagrumForErsattningstyp = lagrum;
    }
}
