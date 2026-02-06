package se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.KlassificeratObjekt;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class ErsattningstypEnligtLagrum extends KlassificeratObjekt
{
    @Builder
    public ErsattningstypEnligtLagrum(UUID id, int revision, String variant, EErsattningstyp ersattningstyp, ELagrumForErsattningstyp lagrumForErsattningstyp)
    {
        super(id, revision, variant);
        this.ersattningstyp = ersattningstyp;
        this.setLagrumForErsattningstyp( lagrumForErsattningstyp );
    }

    @NotNull( message = "An Ersattningstyp needs to be set.")
    private EErsattningstyp ersattningstyp;

    @Nullable
    private ELagrumForErsattningstyp lagrumForErsattningstyp;

    private void setLagrumForErsattningstyp( ELagrumForErsattningstyp lagrumForErsattningstyp)
    {
        if(ersattningstyp != null && lagrumForErsattningstyp != null && ersattningstyp.getLagrumForErsattningstyp() != lagrumForErsattningstyp)
        {
            throw new IllegalArgumentException("Ersättningstyp " + ersattningstyp + " hör till " + ersattningstyp.getLagrumForErsattningstyp() +
                    ", inte " + lagrumForErsattningstyp);
        }
        this.lagrumForErsattningstyp = lagrumForErsattningstyp;
    }
}
