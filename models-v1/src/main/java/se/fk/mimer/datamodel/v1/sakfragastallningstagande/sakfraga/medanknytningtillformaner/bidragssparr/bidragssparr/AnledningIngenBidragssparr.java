package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.medanknytningtillformaner.bidragssparr.bidragssparr;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.sakfraga.medanknytningtillformaner.IngenSparrAnledning;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnledningIngenBidragssparr
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private IngenSparrAnledning ingenSparrAnledning;
}
