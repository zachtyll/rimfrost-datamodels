package se.fk.mimer.producermodels.v2.model.produceratresultat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.KlassificeratObjekt;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.Ersattning;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.produceratresultat.krav.Krav;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.BedomdInkomst;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdkostnad.BedomdKostnad;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.Bidragssparr;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.Intyg;
import se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning.PeriodTillampligLagstiftning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.svensksocialforsakringsperiod.SvenskSocialforsakringsperiod;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = Ersattning.class, name = "Ersattning" ),
        @JsonSubTypes.Type( value = Utforare.class, name = "Utforare" ),
        @JsonSubTypes.Type( value = Intyg.class, name = "Intyg" ),
        @JsonSubTypes.Type( value = SvenskSocialforsakringsperiod.class, name = "SvenskSocialforsakringsperiod" ),
        @JsonSubTypes.Type( value = PeriodTillampligLagstiftning.class, name = "PeriodTillampligLagstiftning" ),
        @JsonSubTypes.Type( value = RattenTillPeriod.class, name = "RattenTillPeriod" ),
        @JsonSubTypes.Type( value = Bedomdarbetsformaga.class, name = "Bedomdarbetsformaga" ),
        @JsonSubTypes.Type( value = KarenstidEnskildNaringsidkare.class, name = "KarenstidEnskildNaringsidkare" ),
        @JsonSubTypes.Type( value = BedomdKostnad.class, name = "BedomdKostnad" ),
        @JsonSubTypes.Type( value = BedomdInkomst.class, name = "BedomdInkomst" ),
        @JsonSubTypes.Type( value = Bidragssparr.class, name = "Bidragssparr" ),
        @JsonSubTypes.Type( value = Krav.class, name = "Krav" )
} )
public abstract class ProduceratResultat extends KlassificeratObjekt
{
    public ProduceratResultat(UUID id, int revision, String variant, UUID faststallsForKundbehov, Person avserPerson,
                              @Nullable Period period, @Nullable String typ, @Nullable String status)
    {
        super(id, revision, variant);
        this.faststallsForKundbehov = faststallsForKundbehov;
        this.avserPerson = avserPerson;
        this.period = period;
        this.typ = typ;
        this.status = status;
    }

    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull( message = "ProduceratResultat must have a faststallsForKundbehov" )
    private UUID faststallsForKundbehov;

    @NotNull
    private Person avserPerson;

    @Nullable
    private Period period;

    @Nullable
    private String typ;

    @Nullable
    private String status;
}
