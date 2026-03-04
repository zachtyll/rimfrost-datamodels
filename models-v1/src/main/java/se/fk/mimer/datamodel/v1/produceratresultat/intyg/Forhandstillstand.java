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
public class Forhandstillstand extends Intyg
{
    @Builder
    public Forhandstillstand( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period giltighetsperiod, String typ, String status,
                              String institution, String beskrivning, ZonedDateTime utfardatDatum, @Nullable Period vardperiod, @Nullable String specifikBeskrivningAvForahndstillstandet,
                              @Nullable String typAvBehandling, @Nullable String vardgivare, @Nullable String lakare)
    {
        super( id, faststallsForKundbehov, revision, avserPerson, giltighetsperiod, typ, status, institution, beskrivning, utfardatDatum);
        this.vardperiod = vardperiod;
        this.specifikBeskrivningAvForahndstillstandet = specifikBeskrivningAvForahndstillstandet;
        this.typAvBehandling = typAvBehandling;
        this.vardgivare = vardgivare;
        this.lakare = lakare;
    }

    @Nullable
    private Period vardperiod;

    @Nullable
    private String specifikBeskrivningAvForahndstillstandet;

    @Nullable
    private String typAvBehandling;

    @Nullable
    private String vardgivare;

    @Nullable
    private String lakare;

}
