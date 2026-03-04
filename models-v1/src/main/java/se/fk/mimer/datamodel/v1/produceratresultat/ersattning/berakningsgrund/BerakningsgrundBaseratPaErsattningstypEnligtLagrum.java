package se.fk.mimer.datamodel.v1.produceratresultat.ersattning.berakningsgrund;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class BerakningsgrundBaseratPaErsattningstypEnligtLagrum
{
    @Builder
    public BerakningsgrundBaseratPaErsattningstypEnligtLagrum(UUID id, int revision, @Nullable EBerakningsgrundRegel berakningsgrundRegel,
                                                              @Nullable EBerakningsgrund berakningsgrund, @Nullable ErsattningstypEnligtLagrum ersattningstypEnligtLagrum)
    {
        this.id = id;
        this.revision = revision;
        this.berakningsgrundRegel = berakningsgrundRegel;
        this.berakningsgrund = berakningsgrund;
        this.ersattningstypEnligtLagrum = ersattningstypEnligtLagrum;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @Nullable
    private EBerakningsgrundRegel berakningsgrundRegel;

    @Nullable
    private EBerakningsgrund berakningsgrund;

    @Nullable
    private ErsattningstypEnligtLagrum ersattningstypEnligtLagrum;
}
