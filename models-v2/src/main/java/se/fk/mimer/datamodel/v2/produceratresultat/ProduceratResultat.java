package se.fk.mimer.datamodel.v2.produceratresultat;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.person.Person;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public abstract class ProduceratResultat
{
    public ProduceratResultat(UUID id, int revision, UUID faststallsForKundbehov, Person avserPerson,
                              @Nullable Period period, @Nullable String typ, @Nullable String status)
    {
        this.id = id;
        this.revision = revision;
        this.faststallsForKundbehov = faststallsForKundbehov;
        this.avserPerson = avserPerson;
        this.period = period;
        this.typ = typ;
        this.status = status;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

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
