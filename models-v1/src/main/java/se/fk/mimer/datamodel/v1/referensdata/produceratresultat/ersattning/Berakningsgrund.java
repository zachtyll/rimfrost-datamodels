package se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning;

import lombok.Getter;

public enum Berakningsgrund
{
    // AS
    INKOMSTBASERAD_AKASSA( BerakningsgrundRegel.AS ),
    ARBETSBASERAD_AKASSA( BerakningsgrundRegel.AS ),

    // SJP
    SGI_A( BerakningsgrundRegel.SJP ),
    SGI_B( BerakningsgrundRegel.SJP );

    @Getter
    private final BerakningsgrundRegel regel;

    Berakningsgrund( BerakningsgrundRegel regel )
    {
        this.regel = regel;
    }
}
