package se.fk.mimer.producermodels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.deserializers.KontouppgiftDeserializer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Getter
@Setter
public class Utforare extends ProduceratResultat {


    @JsonDeserialize(using = KontouppgiftDeserializer.class )
    @NotEmpty(message = "Utförare must have an utforarkonto")
    private List<Kontouppgift> utforarkonto;

    @JsonDeserialize(using = KontouppgiftDeserializer.class )
    private List<Kontouppgift> betaltjanstkonto;

    @Builder
    public Utforare( UUID id, UUID faststallsForKundbehov, int version, String avserPerson, Period period, String typ, String status, List<Kontouppgift> utforarkonto, List<Kontouppgift> betaltjanstkonto )
    {
        super( id, faststallsForKundbehov, version, avserPerson, period, typ, status );
        this.utforarkonto = Optional.ofNullable( utforarkonto ).orElse( Collections.emptyList() );
        this.betaltjanstkonto = Optional.ofNullable( betaltjanstkonto ).orElse( Collections.emptyList() );
    }
}
