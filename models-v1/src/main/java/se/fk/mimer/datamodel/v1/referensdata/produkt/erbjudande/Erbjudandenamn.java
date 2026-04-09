package se.fk.mimer.datamodel.v1.referensdata.produkt.erbjudande;

import lombok.Getter;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Formanstyp;


public enum Erbjudandenamn
{
    // SJP
    ANSOKA_OM_SJUKPENNING( Formanstyp.SJUKPENNING ),
    ANSOKA_OM_KOSTNADSERSATTNING( Formanstyp.SJUKPENNING ),
    ANSOKA_OM_FOREBYGGANDE_SJUKPENNING( Formanstyp.SJUKPENNING ),

    // FP
    ANSOKA_OM_FORALDRAPENNING( Formanstyp.FORALDRAPENNING ),
    GE_BORT_DAGAR( Formanstyp.FORALDRAPENNING );


    @Getter
    private final Formanstyp formanstyp;

    Erbjudandenamn( Formanstyp formanstyp ){
        this.formanstyp = formanstyp;
    }
}


