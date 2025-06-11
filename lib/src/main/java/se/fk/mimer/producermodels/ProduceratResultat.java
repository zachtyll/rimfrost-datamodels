package se.fk.mimer.producermodels;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = Ersattning.class, name = "Ersattning" ),
        @JsonSubTypes.Type( value = Utforare.class, name = "Utforare" )
} )
public abstract class ProduceratResultat implements DataObject
{

    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "ProduceratResultat must have a id" )
    private UUID id;

    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "ProduceratResultat must have a faststallsForKundbehov" )
    @Getter
    private UUID faststallsForKundbehov;

    @NotNull( message = "ProduceratResultat must have a version" )
    private int version;

    @NotBlank( message = "ProduceratResultat must have a avserPerson" )
    private String avserPerson;

    @NotNull( message = "ProduceratResultat must have a period" )
    private Period period;

    @NotBlank( message = "ProduceratResultat must have a typ" )
    private String typ;

    @NotBlank( message = "ProduceratResultat must have a status" )
    private String status;

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

    public Optional<String> getAvserPerson()
    {
        return Optional.ofNullable( avserPerson );
    }

    public Optional<Period> getPeriod()
    {
        return Optional.ofNullable( period );
    }

    public Optional<String> getTyp()
    {
        return Optional.ofNullable( typ );
    }

    public Optional<String> getStatus()
    {
        return Optional.ofNullable( status );
    }
}
