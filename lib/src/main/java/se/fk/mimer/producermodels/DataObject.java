package se.fk.mimer.producermodels;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = Kundbehov.class, name = "Kundbehov" ),
        @JsonSubTypes.Type( value = FysiskPerson.class, name = "FysiskPerson" ),
        @JsonSubTypes.Type( value = ProduceratResultat.class, name = "ProduceratResultat" ),
        @JsonSubTypes.Type( value = Kundbehovsflode.class, name = "Kundbehovsflode" ),
        @JsonSubTypes.Type( value = RollIKundbehov.class, name = "RollIKundbehov" ),
        @JsonSubTypes.Type( value = Beslut.class, name = "Beslut" ),
        @JsonSubTypes.Type( value = Ersattning.class, name = "Ersattning" ),
        @JsonSubTypes.Type( value = Utforare.class, name = "Utforare"),
        @JsonSubTypes.Type( value = StatligtStod.class, name = "StatligtStod")
} )
public interface DataObject
{
    UUID getId();

    int getVersion();
}
