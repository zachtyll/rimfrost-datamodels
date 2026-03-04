package se.fk.mimer.datamodel.v1.produceratresultat;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.person.Person;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class Bedomdarbetsformaga extends ProduceratResultat
{
    @Builder
    public Bedomdarbetsformaga( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson,
                                Period period, String typ, String status, @Nullable String omfattning )
    {
        super( id, revision, faststallsForKundbehov,  avserPerson, period, typ, status );
        this.omfattning = omfattning;
    }

    @Nullable
    private String omfattning;
}
