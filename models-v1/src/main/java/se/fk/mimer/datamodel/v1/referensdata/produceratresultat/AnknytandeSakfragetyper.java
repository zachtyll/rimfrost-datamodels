package se.fk.mimer.datamodel.v1.referensdata.produceratresultat;

import lombok.Getter;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Formanstyp;

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
