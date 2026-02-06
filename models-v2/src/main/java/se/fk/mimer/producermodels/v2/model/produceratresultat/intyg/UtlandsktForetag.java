package se.fk.mimer.producermodels.v2.model.produceratresultat.intyg;

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
public class UtlandsktForetag extends KlassificeratObjekt
{
    @Builder
    public UtlandsktForetag( UUID id, int revision, String variant, String namn, String organisationsnummer)
    {
        super(id, revision, variant);
        this.namn = namn;
        this.organisationsnummer = organisationsnummer;
    }

    @NotNull( message = "UtlandsktForetag has to have a name registered to it.")
    private String namn;

    @NotNull( message = "UtlandsktForetag has to have an organisationsnummer registered to it.")
    private String organisationsnummer;

}
