package se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.omfattning;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.KlassificeratObjekt;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class OmfattningBaseratPaErsattningstypEnligtLagrum extends KlassificeratObjekt
{
    @Builder
    public OmfattningBaseratPaErsattningstypEnligtLagrum(UUID id, int revision, String variant, double procentAvErsattning,
                                                         @Nullable ErsattningstypEnligtLagrum ersattningstypEnligtLagrum)
    {
        super(id, revision, variant);
        this.procentAvErsattning = procentAvErsattning;
        this.ersattningstypEnligtLagrum = ersattningstypEnligtLagrum;
    }

    @NotNull( message = "A percentage has to be set, minimum of 0.0")
    private double procentAvErsattning;

    @Nullable
    private ErsattningstypEnligtLagrum ersattningstypEnligtLagrum;
}
