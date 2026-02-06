package se.fk.mimer.producermodels.v2.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
@Builder
public class StatligtStod implements DataObject
{
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    private Period period;

    private String stodtyp;

    public Optional<Period> getPeriod()
    {
        return Optional.ofNullable( period );
    }

    public Optional<String> getStodtyp()
    {
        return Optional.ofNullable( stodtyp );
    }
}
