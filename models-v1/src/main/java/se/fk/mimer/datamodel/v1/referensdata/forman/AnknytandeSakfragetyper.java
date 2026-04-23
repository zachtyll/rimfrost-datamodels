package se.fk.mimer.datamodel.v1.referensdata.forman;

import lombok.Getter;

public enum AnknytandeSakfragetyper
{
    // US
    BIDRAGSSKYLDIGHET( Formanstyp.UNDERHALLSSTOD),

    // FP
    OVERFORING_AV_DAGAR( Formanstyp.FORALDRAPENNING ),

    // Gemensam
    SGI ( Formanstyp.GEMENSAM ),
    BIDRAGSSPARR ( Formanstyp.GEMENSAM );
    @Getter
    private final Formanstyp formanstyp;

    AnknytandeSakfragetyper( Formanstyp formanstyp ){
        this.formanstyp = formanstyp;
    }
}
