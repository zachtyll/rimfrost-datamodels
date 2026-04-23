package se.fk.mimer.datamodel.v1.referensdata.forman;

import lombok.Getter;

public enum BerattigadeSakfragetyper
{
    // SJP
    SJUKPENNING( Formanstyp.SJUKPENNING ),
    SJUKPENNING_SARKSILDA_FALL( Formanstyp.SJUKPENNING ),
    ERSATTNING_FOR_ARBETSRESOR( Formanstyp.SJUKPENNING ),

    // RHE
    REHABILITERINGSERSATTNING( Formanstyp.REHABILITERINGSERSATTNING ),
    REHABPENNING_SARSKILDA_FALL( Formanstyp.REHABILITERINGSERSATTNING );

    @Getter
    private final Formanstyp formanstyp;

    BerattigadeSakfragetyper( Formanstyp formanstyp ){
        this.formanstyp = formanstyp;
    }
}
