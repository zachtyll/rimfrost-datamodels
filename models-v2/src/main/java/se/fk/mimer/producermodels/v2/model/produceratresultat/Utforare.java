package se.fk.mimer.producermodels.v2.model.produceratresultat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.deserializers.KontouppgiftDeserializer;
import se.fk.mimer.producermodels.v2.model.Kontouppgift;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Getter
@Setter
public class Utforare extends ProduceratResultat
{

    @JsonDeserialize(using = KontouppgiftDeserializer.class )
    private List<Kontouppgift> utforarkonto;

    @JsonDeserialize(using = KontouppgiftDeserializer.class )
    private List<Kontouppgift> betaltjanstkonto;

    @Builder
    public Utforare( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period period,
                     String typ, String status, List<Kontouppgift> utforarkonto, List<Kontouppgift> betaltjanstkonto )
    {
        super( id, revision, variant, faststallsForKundbehov, avserPerson, period, typ, status );
        this.utforarkonto = Optional.ofNullable( utforarkonto ).orElse( Collections.emptyList() );
        this.betaltjanstkonto = Optional.ofNullable( betaltjanstkonto ).orElse( Collections.emptyList() );
    }
}
