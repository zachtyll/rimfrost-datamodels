package se.fk.mimer.producermodels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.deserializers.ZonedDateTimeDeserializer;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Builder
public class Kundbehovsflode implements DataObject
{
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "Kundbehovsflode must have a kundbehov" )
    private UUID id;

    @NotNull( message = "Kundbehovsflode must have a version" )
    private int version;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    @Getter
    @NotNull
    private ZonedDateTime avslutad;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    @Getter
    @NotNull( message = "Kundbehovsflode must have a skapad timestamp" )
    private ZonedDateTime skapad;

    @JsonDeserialize( contentUsing = UUIDDeserializer.class )
    @Getter
    @NotEmpty( message = "Kundbehovsflode must have a hanterarKundbehov" )
    private List<UUID> hanterarKundbehov;

    @Nullable
    private String arendeId;

    public Optional<String> getArendeId()
    {
        return Optional.ofNullable( arendeId );
    }

    @Override
    public UUID getId()
    {
        return id;
    }

    @Override
    public int getVersion()
    {
        return version;
    }
}
