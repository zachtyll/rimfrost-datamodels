package se.fk.mimer.datamodel.v1.handlaggning;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.yrkande.Yrkande;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
public class Handlaggning
{
    @Builder
    public Handlaggning( UUID id, int revision, ZonedDateTime avslutad, ZonedDateTime skapad, List<UUID> yrkandeIds, List<Yrkande> yrkanden, @Nullable String arendeId )
    {
        this.id = id;
        this.revision = revision;
        this.avslutad = avslutad;
        this.skapad = skapad;
        this.yrkanden = Optional.ofNullable( yrkanden ).orElse( Collections.emptyList() );
        this.yrkandeIds = Optional.ofNullable( yrkandeIds ).orElse( Collections.emptyList() );
        this.arendeId = arendeId;
    }
    @NotNull( message = "Handlaggning must have a proper ID." )
    @Getter
    private UUID id;

    @Nullable
    private String arendeId;

    @Min(1)
    @Getter
    private int revision;

    private ZonedDateTime avslutad;

    private ZonedDateTime skapad;

    @Getter
    private List<UUID> yrkandeIds;

    @Getter
    private List<Yrkande> yrkanden;

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
