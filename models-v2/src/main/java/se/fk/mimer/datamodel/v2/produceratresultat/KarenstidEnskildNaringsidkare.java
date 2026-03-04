package se.fk.mimer.datamodel.v2.produceratresultat;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.person.EnskildNaringsidkare;
import se.fk.mimer.datamodel.v2.person.Person;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class KarenstidEnskildNaringsidkare extends ProduceratResultat
{
    @Builder
    public KarenstidEnskildNaringsidkare( UUID id, int revision, UUID faststallsForKundbehov, Person avserPerson, Period giltighetsperiod, String typ, String status,
                                          int antalDagar, EnskildNaringsidkare[] naringsidkare )
    {
        super( id, revision, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
        this.antalDagar = antalDagar;
        this.naringsidkare = naringsidkare;
    }

    @NotNull
    private int antalDagar;

    @NotNull( message = "If no EnskildNaringsidkare exist then leave the array empty." )
    private EnskildNaringsidkare[] naringsidkare;

}
