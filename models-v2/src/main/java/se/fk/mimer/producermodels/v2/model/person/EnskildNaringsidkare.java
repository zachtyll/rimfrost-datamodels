package se.fk.mimer.producermodels.v2.model.person;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.DataObject;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class EnskildNaringsidkare implements DataObject
{
    @Builder
    public EnskildNaringsidkare( UUID id, int revision )
    {
        this.id = id;
        this.revision = revision;
    }

    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull
    private UUID id;

    @NotNull
    private int revision;
}
