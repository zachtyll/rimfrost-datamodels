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

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = FysiskPerson.class, name = "FysiskPerson" ),
} )
public abstract class Person implements DataObject
{
    @JsonDeserialize( using = UUIDDeserializer.class )
    protected UUID id;

    @NotBlank( message = "Person must have a kundid" )
    protected String kundid;

    @NotNull( message = "Person must have a version" )
    protected int version;

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
