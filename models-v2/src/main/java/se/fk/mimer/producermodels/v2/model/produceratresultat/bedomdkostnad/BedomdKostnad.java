package se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdkostnad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.Periodisering;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ProduceratResultat;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
public class BedomdKostnad extends ProduceratResultat
{
    @Builder
    public BedomdKostnad( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period period, String typ, String status,
                          double bedomdKostnad, Kostnadstyp kostnadstyp, Periodisering periodisering )
    {
        super( id, revision, variant, faststallsForKundbehov, avserPerson, period, typ, status );
        this.bedomdKostnad = bedomdKostnad;
        this.kostnadstyp = kostnadstyp;
        this.periodisering = periodisering;
    }

    @Getter
    private double bedomdKostnad;

    @Getter
    private Kostnadstyp kostnadstyp;

    @Getter
    private Periodisering periodisering;
}
