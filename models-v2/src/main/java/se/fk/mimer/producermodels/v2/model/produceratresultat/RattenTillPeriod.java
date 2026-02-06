package se.fk.mimer.producermodels.v2.model.produceratresultat;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class RattenTillPeriod extends ProduceratResultat
{
    @Builder
    public RattenTillPeriod( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period period, String typ, String status,
                             @Nullable String omfattningstyp, @Nullable String ersattningstyp )
    {
        super(id, revision, variant, faststallsForKundbehov, avserPerson, period, typ, status);
        this.omfattningstyp = omfattningstyp;
        this.ersattningstyp = ersattningstyp;
    }

    @Nullable
    private String omfattningstyp;

    @Nullable
    private String ersattningstyp;
}
