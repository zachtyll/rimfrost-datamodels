package se.fk.mimer.datamodel.v1.person;

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
public class EnskildNaringsidkare
{
    @Builder
    public EnskildNaringsidkare( UUID id, int revision )
    {
        this.id = id;
        this.revision = revision;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;
}
