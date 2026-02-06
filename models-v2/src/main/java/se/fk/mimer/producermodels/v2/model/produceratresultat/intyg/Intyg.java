package se.fk.mimer.producermodels.v2.model.produceratresultat.intyg;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ProduceratResultat;

import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = IVIntyg.class, name = "IVIntyg" ),
        @JsonSubTypes.Type( value = Forhandstillstand.class, name = "Forhandstillstand/S2" ),
        @JsonSubTypes.Type( value = EUKort.class, name = "EUKort" ),
        @JsonSubTypes.Type( value = IntygOmTillampligLagstiftning.class, name = "IntygOmTillampligLagstiftning" )
} )
public class Intyg extends ProduceratResultat
{
    public Intyg( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period giltighetsperiod, String typ, String status,
                  @Nullable String institution, @Nullable String beskrivning, @Nullable ZonedDateTime utfardatDatum )
    {
        super( id, revision, variant, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
        this.institution = institution;
        this.beskrivning = beskrivning;
        this.utfardatDatum = utfardatDatum;
    }

    @Nullable
    private String institution;

    @Nullable
    private String beskrivning;

    @Nullable
    private ZonedDateTime utfardatDatum;

}
