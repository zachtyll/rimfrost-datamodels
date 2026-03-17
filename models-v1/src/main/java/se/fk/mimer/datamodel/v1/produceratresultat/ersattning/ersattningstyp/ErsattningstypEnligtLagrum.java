package se.fk.mimer.datamodel.v1.produceratresultat.ersattning.ersattningstyp;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.Ersattningstyp;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.LagrumForErsattningstyp;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class ErsattningstypEnligtLagrum
{
    @Builder
    public ErsattningstypEnligtLagrum( UUID id, int revision, Ersattningstyp ersattningstyp, LagrumForErsattningstyp lagrumForErsattningstyp)
    {
        this.id = id;
        this.revision = revision;
        this.ersattningstyp = ersattningstyp;
        this.setLagrumForErsattningstyp( lagrumForErsattningstyp );
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull( message = "An Ersattningstyp needs to be set.")
    private Ersattningstyp ersattningstyp;

    @Nullable
    private LagrumForErsattningstyp lagrumForErsattningstyp;

    private void setLagrumForErsattningstyp( LagrumForErsattningstyp lagrumForErsattningstyp)
    {
        if(ersattningstyp != null && lagrumForErsattningstyp != null && ersattningstyp.getLagrumForErsattningstyp() != lagrumForErsattningstyp)
        {
            throw new IllegalArgumentException("Ersättningstyp " + ersattningstyp + " hör till " + ersattningstyp.getLagrumForErsattningstyp() +
                    ", inte " + lagrumForErsattningstyp);
        }
        this.lagrumForErsattningstyp = lagrumForErsattningstyp;
    }
}
