package se.fk.mimer.datamodel.v1.produceratresultat.ersattning.omfattning;

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
public class OmfattningBaseratPaErsattningstypEnligtLagrum
{
    @Builder
    public OmfattningBaseratPaErsattningstypEnligtLagrum(UUID id, int revision, double procentAvErsattning,
                                                         @Nullable ErsattningstypEnligtLagrum ersattningstypEnligtLagrum)
    {
        this.id = id;
        this.revision = revision;
        this.procentAvErsattning = procentAvErsattning;
        this.ersattningstypEnligtLagrum = ersattningstypEnligtLagrum;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull( message = "A percentage has to be set, minimum of 0.0")
    private double procentAvErsattning;

    @Nullable
    private ErsattningstypEnligtLagrum ersattningstypEnligtLagrum;
}
