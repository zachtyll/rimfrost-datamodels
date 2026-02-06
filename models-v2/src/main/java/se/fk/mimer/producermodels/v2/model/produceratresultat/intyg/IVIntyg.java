package se.fk.mimer.producermodels.v2.model.produceratresultat.intyg;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class IVIntyg extends Intyg
{
    @Builder
    public IVIntyg( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period giltighetsperiod, String typ, String status,
                    String institution, String beskrivning, ZonedDateTime utfardatDatum, @Nullable IVIntygstyp intygstyp )
    {
        super( id, faststallsForKundbehov, revision, variant, avserPerson, giltighetsperiod, typ, status, institution, beskrivning, utfardatDatum);
        this.intygstyp = intygstyp;
    }

    @Nullable
    private IVIntygstyp intygstyp;

}
