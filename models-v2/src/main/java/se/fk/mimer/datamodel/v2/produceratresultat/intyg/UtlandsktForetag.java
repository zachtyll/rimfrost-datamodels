package se.fk.mimer.datamodel.v2.produceratresultat.intyg;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class UtlandsktForetag
{
    @Builder
    public UtlandsktForetag( UUID id, int revision, String namn, String organisationsnummer)
    {
        this.id = id;
        this.revision = revision;
        this.namn = namn;
        this.organisationsnummer = organisationsnummer;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull( message = "UtlandsktForetag has to have a name registered to it.")
    private String namn;

    @NotNull( message = "UtlandsktForetag has to have an organisationsnummer registered to it.")
    private String organisationsnummer;

}
