package se.fk.mimer.datamodel.v1.produceratresultat.ersattning.beloppstyp;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.Beloppstyp;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.BeloppstypKategori;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class BeloppstypBaseratPaErsattningstypEnligtLagrum
{
    @Builder
    public BeloppstypBaseratPaErsattningstypEnligtLagrum( UUID id, int revision, @Nullable BeloppstypKategori beloppstypKategori,
                                                          @Nullable Beloppstyp beloppstyp, @Nullable ErsattningstypEnligtLagrum ersattningstypEnligtLagrum)
    {
        this.id = id;
        this.revision = revision;
        this.beloppstypKategori = beloppstypKategori;
        this.beloppstyp = beloppstyp;
        this.ersattningstypEnligtLagrum = ersattningstypEnligtLagrum;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @Nullable
    private BeloppstypKategori beloppstypKategori;

    @Nullable
    private Beloppstyp beloppstyp;

    @Nullable
    private ErsattningstypEnligtLagrum ersattningstypEnligtLagrum;
}
