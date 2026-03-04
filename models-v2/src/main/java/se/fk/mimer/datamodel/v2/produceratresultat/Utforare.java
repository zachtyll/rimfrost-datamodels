package se.fk.mimer.datamodel.v2.produceratresultat;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Kontouppgift;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.person.Person;

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

    private List<Kontouppgift> utforarkonto;

    private List<Kontouppgift> betaltjanstkonto;

    @Builder
    public Utforare( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period period,
                     String typ, String status, List<Kontouppgift> utforarkonto, List<Kontouppgift> betaltjanstkonto )
    {
        super( id, revision, faststallsForKundbehov, avserPerson, period, typ, status );
        this.utforarkonto = Optional.ofNullable( utforarkonto ).orElse( Collections.emptyList() );
        this.betaltjanstkonto = Optional.ofNullable( betaltjanstkonto ).orElse( Collections.emptyList() );
    }
}
