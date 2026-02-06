package se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp;

import jakarta.annotation.Nullable;
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
public class BeloppstypBaseratPaErsattningstypEnligtLagrum extends KlassificeratObjekt
{
    @Builder
    public BeloppstypBaseratPaErsattningstypEnligtLagrum(UUID id, int revision, String variant, @Nullable EBeloppstypKategori beloppstypKategori,
                                                         @Nullable EBeloppstyp beloppstyp, @Nullable ErsattningstypEnligtLagrum ersattningstypEnligtLagrum)
    {
        super(id, revision, variant);
        this.beloppstypKategori = beloppstypKategori;
        this.beloppstyp = beloppstyp;
        this.ersattningstypEnligtLagrum = ersattningstypEnligtLagrum;
    }

    @Nullable
    private EBeloppstypKategori beloppstypKategori;

    @Nullable
    private EBeloppstyp beloppstyp;

    @Nullable
    private ErsattningstypEnligtLagrum ersattningstypEnligtLagrum;
}
