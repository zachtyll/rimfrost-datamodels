package se.fk.mimer.producermodels.v2.model.person;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.KlassificeratObjekt;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = FysiskPerson.class, name = "FysiskPerson" ),
        @JsonSubTypes.Type( value = JuridiskPerson.class, name = "JuridiskPerson" )
} )
public abstract class Person extends KlassificeratObjekt
{
    public Person(UUID id, int revision, String variant, @Nullable String kundid, Map<UUID, RollIKundbehov> rollIKundbehov)
    {
        super(id, revision, variant);
        this.kundid = kundid;
        this.rollIKundbehov = rollIKundbehov;
    }

    @Nullable
    protected String kundid;

    @NotNull
    @Getter
    protected Map<UUID, RollIKundbehov> rollIKundbehov;

    public Optional<String> getKundid()
    {
        return Optional.ofNullable( kundid );
    }
}
