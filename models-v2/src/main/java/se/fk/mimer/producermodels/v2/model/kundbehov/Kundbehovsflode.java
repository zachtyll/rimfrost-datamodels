package se.fk.mimer.producermodels.v2.model.kundbehov;

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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
public class Kundbehovsflode implements DataObject
{
    @Builder
    public Kundbehovsflode( UUID id, int revision, ZonedDateTime avslutad, ZonedDateTime skapad, List<UUID> hanterarKundbehov, @Nullable String arendeId )
    {
        this.id = id;
        this.revision = revision;
        this.avslutad = avslutad;
        this.skapad = skapad;
        this.hanterarKundbehov = Optional.ofNullable( hanterarKundbehov ).orElse( Collections.emptyList() );
        this.arendeId = arendeId;
    }
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "Kundbehovsflode must have a proper ID." )
    @Getter
    private UUID id;

    @NotNull
    @Getter
    private int revision;

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
}
