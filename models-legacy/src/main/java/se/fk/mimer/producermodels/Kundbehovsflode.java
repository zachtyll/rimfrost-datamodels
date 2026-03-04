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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Builder
public class Kundbehovsflode implements DataObject
{
    public Kundbehovsflode( UUID id, int version, ZonedDateTime avslutad, ZonedDateTime skapad, List<UUID> hanterarKundbehov, String arendeId )
    {
        this.id = id;
        this.version = version;
        this.avslutad = avslutad;
        this.skapad = skapad;
        this.hanterarKundbehov = Optional.ofNullable( hanterarKundbehov ).orElse( Collections.emptyList() );
        this.arendeId = arendeId;
    }

    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "Kundbehovsflode must have a kundbehov" )
    private UUID id;

    @NotNull( message = "Kundbehovsflode must have a version" )
    private int version;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    private ZonedDateTime avslutad;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    private ZonedDateTime skapad;

    @JsonDeserialize( contentUsing = UUIDDeserializer.class )
    @Getter
    private List<UUID> hanterarKundbehov;

    @Nullable
    private String arendeId;

    public Optional<ZonedDateTime> getAvslutad()
    {
        return Optional.ofNullable( avslutad );
    }

    public Optional<ZonedDateTime> getSkapad()
    {
        return Optional.ofNullable( skapad );
    }

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
