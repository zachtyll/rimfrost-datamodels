package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Periodisering;
import se.fk.mimer.datamodel.v1.inkomst.Inkomst;

import java.time.ZonedDateTime;
import java.util.Collection;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class StallningstagenInkomst extends StallningstagandeIHandlaggningen
{
    @NotNull
    private StallningstagnaInkomsttyper bedomdInkomstTyp;
    @NotNull
    private Double bedomdInkomstBelopp;
    @NotNull
    private ZonedDateTime bedomdInkomstDatum;
    @NotNull
    private ZonedDateTime bedomdInkomstFrom;
    @NotNull
    private ZonedDateTime bedomdInkomstTom;
    @NotNull
    private Periodisering periodisering;
    @NotNull
    private Collection<Inkomst> avserInkomst;
}
