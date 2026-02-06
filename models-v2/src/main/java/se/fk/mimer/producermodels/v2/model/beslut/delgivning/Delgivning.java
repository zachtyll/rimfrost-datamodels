package se.fk.mimer.producermodels.v2.model.beslut.delgivning;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.deserializers.ZonedDateTimeDeserializer;
import se.fk.mimer.producermodels.v2.model.DataObject;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class Delgivning implements DataObject
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
    @JsonDeserialize( using = UUIDDeserializer.class )
    private UUID id;

    @NotNull
    @JsonDeserialize
    private int revision;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    @Nullable
    private ZonedDateTime delgivningsdatum;

    @Nullable
    private Delgivningstyp delgivningstyp;

    @Nullable
    private UUID beslutId;

}
