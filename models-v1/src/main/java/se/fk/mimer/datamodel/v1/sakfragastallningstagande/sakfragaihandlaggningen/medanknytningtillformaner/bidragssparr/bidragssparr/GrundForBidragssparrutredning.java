package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.bidragssparr;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.sakfraga.medanknytningtillformaner.Bidragssparrgrund;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GrundForBidragssparrutredning
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Bidragssparrgrund bidragssparrgrund;
}
