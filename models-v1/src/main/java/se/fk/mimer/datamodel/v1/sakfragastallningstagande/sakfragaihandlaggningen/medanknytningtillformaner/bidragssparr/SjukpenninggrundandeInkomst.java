package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Periodisering;
import se.fk.mimer.datamodel.v1.inkomst.Inkomst;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.SakfragaIHandlaggningen;

import java.time.ZonedDateTime;
import java.util.Collection;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class SjukpenninggrundandeInkomst extends SakfragaIHandlaggningen
{
    @NotNull
    private SFBInkomsttyp SFBInkomstTyp;
    @NotNull
    private Double SFBInkomstBelopp;
    @NotNull
    private ZonedDateTime SFBInkomstDatum;
    @NotNull
    private ZonedDateTime SFBInkomstFrom;
    @NotNull
    private ZonedDateTime SFBInkomstTom;
    @NotNull
    private Periodisering periodisering;
    @NotNull
    private Collection<Inkomst> avserInkomst;
}
