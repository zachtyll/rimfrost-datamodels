package se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.KlassificeratObjekt;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class BerakningsgrundBaseratPaErsattningstypEnligtLagrum extends KlassificeratObjekt
{
    @Builder
    public BerakningsgrundBaseratPaErsattningstypEnligtLagrum(UUID id, int revision, String variant, @Nullable EBerakningsgrundRegel berakningsgrundRegel,
                                                              @Nullable EBerakningsgrund berakningsgrund, @Nullable ErsattningstypEnligtLagrum ersattningstypEnligtLagrum)
    {
        super(id, revision, variant);
        this.berakningsgrundRegel = berakningsgrundRegel;
        this.berakningsgrund = berakningsgrund;
        this.ersattningstypEnligtLagrum = ersattningstypEnligtLagrum;
    }

    @Nullable
    private EBerakningsgrundRegel berakningsgrundRegel;

    @Nullable
    private EBerakningsgrund berakningsgrund;

    @Nullable
    private ErsattningstypEnligtLagrum ersattningstypEnligtLagrum;
}
