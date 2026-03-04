package se.fk.mimer.datamodel.v2.beslut.delgivning;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class Delgivning
{
    @Builder
    public Delgivning(UUID id, int revision, @Nullable ZonedDateTime delgivningsdatum,
                      @Nullable Delgivningstyp delgivningstyp, @Nullable UUID beslutId)
    {
        this.id = id;
        this.revision = revision;
        this.delgivningsdatum = delgivningsdatum;
        this.delgivningstyp = delgivningstyp;
        this.beslutId = beslutId;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @Nullable
    private ZonedDateTime delgivningsdatum;

    @Nullable
    private Delgivningstyp delgivningstyp;

    @Nullable
    private UUID beslutId;

}
