package se.fk.mimer.datamodel.v1.produceratresultat.ersattning.berakningsgrund;

import lombok.Getter;

public enum EBerakningsgrund
{
    // AS
    INKOMSTBASERAD_AKASSA( EBerakningsgrundRegel.AS ),
    ARBETSBASERAD_AKASSA( EBerakningsgrundRegel.AS ),

    // SJP
    SGI_A( EBerakningsgrundRegel.SJP ),
    SGI_B( EBerakningsgrundRegel.SJP );

    @Getter
    private final EBerakningsgrundRegel regel;

    EBerakningsgrund( EBerakningsgrundRegel regel )
    {
        this.regel = regel;
    }
}
