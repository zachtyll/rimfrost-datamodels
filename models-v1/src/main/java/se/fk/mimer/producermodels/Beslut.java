package se.fk.mimer.producermodels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
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
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Builder
public class Beslut implements DataObject
{
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "Beslut must have an id" )
    private UUID id;

    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "Beslut must have an avserKundbehov" )
    @Getter
    private UUID avserKundbehov;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    @Nullable
    private ZonedDateTime beslutsdatum;

    @Nullable
    private String beslutstyp;

    @Nullable
    private String beslutsutfall;

    @Nullable
    private String beslutsfattareId;

    @Nullable
    private String beslutEnligtLagrum;

    @Nullable
    private String beslutandeOrganisation;

    @Nullable
    private String avslagsAnledning;

    @NotNull( message = "Beslut must have a version" )
    private int version;

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

    public Optional<ZonedDateTime> getBeslutsdatum()
    {
        return Optional.ofNullable( beslutsdatum );
    }

    public Optional<String> getBeslutstyp()
    {
        return Optional.ofNullable( beslutstyp );
    }

    public Optional<String> getBeslutsutfall()
    {
        return Optional.ofNullable( beslutsutfall );
    }

    public Optional<String> getBeslutsfattareId()
    {
        return Optional.ofNullable( beslutsfattareId );
    }

    public Optional<String> getBeslutEnligtLagrum()
    {
        return Optional.ofNullable( beslutEnligtLagrum );
    }

    public Optional<String> getBeslutandeOrganisation()
    {
        return Optional.ofNullable( beslutandeOrganisation );
    }

    public Optional<String> getAvslagsAnledning()
    {
        return Optional.ofNullable( avslagsAnledning );
    }
}
