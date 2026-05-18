package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.sakfraga.medanknytningtillformaner.SFBInkomsttyper;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class SFBInkomsttyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private SFBInkomsttyper SFBInkomstTyp;
}
