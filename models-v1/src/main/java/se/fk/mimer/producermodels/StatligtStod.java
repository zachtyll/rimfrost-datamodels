package se.fk.mimer.producermodels;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class StatligtStod implements DataObject {
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "StatligtStod must have an id" )
    private UUID id;

    @NotNull( message = "StatligtStod must have a version" )
    private int version;

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

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getVersion()
    {
        return version;
    }
}
