package se.fk.mimer.producermodels.v2.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import se.fk.mimer.producermodels.v2.model.beslut.Beslut;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.Ersattning;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehov;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsflode;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;
import se.fk.mimer.producermodels.v2.model.person.FysiskPerson;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ProduceratResultat;
import se.fk.mimer.producermodels.v2.model.produceratresultat.Utforare;
import se.fk.mimer.producermodels.v2.model.produkt.Erbjudande;
import se.fk.mimer.producermodels.v2.model.produkt.Produkt;
import se.fk.mimer.producermodels.v2.model.produkt.RollIProdukt;

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
        @JsonSubTypes.Type( value = StatligtStod.class, name = "StatligtStod"),
        @JsonSubTypes.Type( value = Kontouppgift.class, name = "Kontouppgift"),
        @JsonSubTypes.Type( value = Erbjudande.class, name = "Erbjudande"),
        @JsonSubTypes.Type( value = RollIProdukt.class, name = "RollIProdukt"),
        @JsonSubTypes.Type( value = Produkt.class, name = "Produkt")
} )
public interface DataObject
{
    UUID getId();

    int getRevision();
}
