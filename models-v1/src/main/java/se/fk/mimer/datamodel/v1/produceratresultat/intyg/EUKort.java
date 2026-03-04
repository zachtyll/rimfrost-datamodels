package se.fk.mimer.datamodel.v1.produceratresultat.intyg;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.person.Person;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class EUKort extends Intyg
{
    @Builder
    public EUKort( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period giltighetsperiod, String typ, String status,
                   String institution, String beskrivning, ZonedDateTime utfardatDatum, @Nullable String kortnummer )
    {
        super( id, faststallsForKundbehov, revision, avserPerson, giltighetsperiod, typ, status, institution, beskrivning, utfardatDatum);
        this.kortnummer = kortnummer;
    }

    @Nullable
    private String kortnummer;
}
