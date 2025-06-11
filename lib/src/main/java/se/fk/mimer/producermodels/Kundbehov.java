package se.fk.mimer.producermodels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Kundbehov implements DataObject
{
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "Kundbehov must have a id" )
    private UUID id;

    @Getter
    @NotBlank( message = "Kundbehov must have a kundbehovsstatus" )
    private String kundbehovsstatus;

    @Getter
    @NotBlank( message = "Kundbehov must contain avsikt" )
    private String avsikt;

    @Nullable
    private String andringsorsak;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    @Getter
    @NotNull( message = "Kundbehov must have a kundbehovsdatum" )
    private ZonedDateTime kundbehovsdatum;

    @Getter
    @NotNull( message = "Kundbehov must have a period" )
    private Period period;

    @Getter
    @NotBlank( message = "Kundbehov must have a avserErbjudande" )
    private String avserErbjudande;

    @Getter
    @NotEmpty( message = "Kundbehov must have a rollIKundbehov" )
    private List<RollIKundbehov> roller;

    @NotNull( message = "Beslut must have a version" )
    private int version;

    public Kundbehov( UUID id, String kundbehovsstatus, String avsikt, @Nullable String andringsorsak, ZonedDateTime kundbehovsdatum, Period period, String avserErbjudande, List<RollIKundbehov> roller, int version )
    {
        this.id = id;
        this.kundbehovsstatus = kundbehovsstatus;
        this.avsikt = avsikt;
        this.andringsorsak = andringsorsak;
        this.kundbehovsdatum = kundbehovsdatum;
        this.period = period;
        this.avserErbjudande = avserErbjudande;
        this.roller = Optional.ofNullable( roller ).orElse( Collections.emptyList() );
        this.version = version;
    }

    @Nullable
    public Optional<String> getAndringsorsak()
    {
        return Optional.ofNullable( andringsorsak );
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
