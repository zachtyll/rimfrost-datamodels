package se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp;

import lombok.Getter;

public enum EErsattningstyp
{
    // FEA
    AKTIVITETSSTOD( ELagrumForErsattningstyp.FEA ),
    BOSTADSERSATTNING( ELagrumForErsattningstyp.FEA ),
    ETABLERINGSERSATTNING( ELagrumForErsattningstyp.FEA ),
    ETABLERINGSTILLAGG( ELagrumForErsattningstyp.FEA ),
    UTVECKLINGSERSATTNING( ELagrumForErsattningstyp.FEA ),
    UTFYLLNADSBIDRAG( ELagrumForErsattningstyp.FEA ),

    // SFB
    ADOPTIONSBIDRAG( ELagrumForErsattningstyp.SFB ),
    AKTIVITETSERSATTNING( ELagrumForErsattningstyp.SFB ),
    ARBETSRESOR( ELagrumForErsattningstyp.SFB ),
    ARBETSSKADA_KOSTNADSERSATTNING( ELagrumForErsattningstyp.SFB ),
    ARBETSSKADELIVRANTA( ELagrumForErsattningstyp.SFB ),
    ARBETSSKAESJUKPENNING( ELagrumForErsattningstyp.SFB ),
    ASSISTANSERSATTNING( ELagrumForErsattningstyp.SFB ),
    BARNBIDRAG_ALLMANT( ELagrumForErsattningstyp.SFB ),
    BARNBIDRAG_FORLANGT( ELagrumForErsattningstyp.SFB ),
    BEGRAVNINGSHJALP( ELagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_ARBETSGIVARE_FOR_KOP_AV_ARBETSPLATSINRIKTAT_REHABILITERINGSSTOD_FOR_ATERGANG_TILL_ARBETE( ELagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_ARBETSHJALPMEDEL( ELagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_ARBETSNARA_STOD( ELagrumForErsattningstyp.SFB ),
    BILSTOD_ANPASSNINGSBIDRAG( ELagrumForErsattningstyp.SFB ),
    BILSTOD_ANSKAFFNINGSBIDRAG( ELagrumForErsattningstyp.SFB ),
    BILSTOD_GRUNDBIDRAG( ELagrumForErsattningstyp.SFB ),
    BILSTOD_KORKORTSUTBILDNING( ELagrumForErsattningstyp.SFB ),
    BILSTOD_TILLAGGSBIDRAG( ELagrumForErsattningstyp.SFB ),
    BOENDETILLAGG( ELagrumForErsattningstyp.SFB ),
    BIDRAG_TILL_KOSTNADER_FOR_BOSTAD( ELagrumForErsattningstyp.SFB ),
    SARSKILT_BIDRAG_FOR_HEMMAVARANDE_BARN( ELagrumForErsattningstyp.SFB ),
    SARSKILT_BIDRAG_FOR_BARN_SOM_BOR_VAXELVIS( ELagrumForErsattningstyp.SFB ),
    UMGANGESBIDRAG_TILL_DEN_SOM_PA_GRUND_AV_VARDNAD_ELLER_UMGANGE_TIDVIS_HAR_BARN_BOENDE_I_SITT_HEM( ELagrumForErsattningstyp.SFB ),
    TILLAGGSBIDRAG_TILL_BARNFAMILJER( ELagrumForErsattningstyp.SFB ),
    BOSTADSSTOD( ELagrumForErsattningstyp.SFB ),
    BOSTADSTILLAGG( ELagrumForErsattningstyp.SFB ),
    EFTERLEVNADSRANTA( ELagrumForErsattningstyp.SFB ),
    ERSATTNING_FOR_ARBETSRESOR_ISTALLET_FOR_SJUKPENNING( ELagrumForErsattningstyp.SFB ),
    ERSATTNING_FOR_HOGA_SJUKLONEKOSTNADER( ELagrumForErsattningstyp.SFB ),
    FORALDRAPENNING( ELagrumForErsattningstyp.SFB ),
    FORALDRAPENNING_I_SAMBAND_MED_BARNS_FODELSE( ELagrumForErsattningstyp.SFB ),
    REHABILITERINGSPENNING_I_SARSKILDA_FALL( ELagrumForErsattningstyp.SFB ),
    REHABILITERING( ELagrumForErsattningstyp.SFB ),
    GRAVIDITETSPENNING( ELagrumForErsattningstyp.SFB ),
    HANDIKAPPSERSATTNING( ELagrumForErsattningstyp.SFB ),
    KIGSSKADEERSATTNING_TILL_SJOMAN( ELagrumForErsattningstyp.SFB ),
    MERKOSTNADSERSATTNING( ELagrumForErsattningstyp.SFB ),
    NARSAENDEPENNING( ELagrumForErsattningstyp.SFB ),
    OMVARDNADSBIDRAG( ELagrumForErsattningstyp.SFB ),
    REHABILITERINGSERSATTNING( ELagrumForErsattningstyp.SFB ),
    REHABILITERINGSERSATTNING_SARSKILT_BIDRAG( ELagrumForErsattningstyp.SFB ),
    SJUKPENNING( ELagrumForErsattningstyp.SFB ),
    SJUKPENNING_I_SARSKILDA_FALL( ELagrumForErsattningstyp.SFB ),
    SARSKILT_BIDRAG( ELagrumForErsattningstyp.SFB ),
    SJUKERSATTNING( ELagrumForErsattningstyp.SFB ),
    SMITTBARAREERSATTNING( ELagrumForErsattningstyp.SFB ),
    ADOPTIONSPENNING( ELagrumForErsattningstyp.SFB ),
    TILLFALLIG_FORALDRAPENNING( ELagrumForErsattningstyp.SFB ),
    SJUKLON( ELagrumForErsattningstyp.SFB ),
    FORLANGT_UNDERHALLSSTOD( ELagrumForErsattningstyp.SFB ),
    BIDRAGSSKYLDIG( ELagrumForErsattningstyp.SFB ),
    UNDERHALLSBIDRAG( ELagrumForErsattningstyp.SFB ),
    UNDERHALLSSTOD( ELagrumForErsattningstyp.SFB ),

    // LAG X
    DAGPENNING_TILL_TOTALFORSVARSPLIKTIGA( ELagrumForErsattningstyp.LAGX ),
    BEGRAVNINGSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA( ELagrumForErsattningstyp.LAGX ),
    FAMILJEPENNING_TILL_TOTALFORSVARSPLIKTIGA( ELagrumForErsattningstyp.LAGX ),
    BOSTADSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA( ELagrumForErsattningstyp.LAGX ),
    NARINGSBIDRAG_TILL_TOTALFORSVARSPLIKTIGA( ELagrumForErsattningstyp.LAGX ),

    // LAG Y
    ERSATTNING_FOR_ETABLERINGSJOBB( ELagrumForErsattningstyp.LAGY );

    @Getter
    private final ELagrumForErsattningstyp lagrumForErsattningstyp;

    EErsattningstyp( ELagrumForErsattningstyp lagrum )
    {
        this.lagrumForErsattningstyp = lagrum;
    }
}
