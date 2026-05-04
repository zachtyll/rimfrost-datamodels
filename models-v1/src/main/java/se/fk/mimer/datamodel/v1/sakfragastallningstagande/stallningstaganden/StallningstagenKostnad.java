package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Periodisering;

import java.time.ZonedDateTime;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class StallningstagenKostnad extends StallningstagandeIHandlaggningen
{
    @NotNull
    private StallningstagnaKostnadstyper bedomdIKostnadTyp;
    @NotNull
    private Double bedomdKonstnadBelopp;
    @NotNull
    private ZonedDateTime bedomdKostnadDatum;
    @NotNull
    private ZonedDateTime bedomdKostnadFrom;
    @NotNull
    private ZonedDateTime bedomdKostnadTom;
    @NotNull
    private Periodisering periodisering;
}
