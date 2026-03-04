package se.fk.mimer.datamodel.v2.produceratresultat.intyg;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.person.Person;
import se.fk.mimer.datamodel.v2.produceratresultat.ProduceratResultat;

import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class Intyg extends ProduceratResultat
{
    public Intyg( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period giltighetsperiod, String typ, String status,
                  @Nullable String institution, @Nullable String beskrivning, @Nullable ZonedDateTime utfardatDatum )
    {
        super(id, revision, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
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
