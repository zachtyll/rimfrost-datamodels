package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.bidragssparr;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.SakfragaIHandlaggningen;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Setter
@Getter
@SuperBuilder
public class Bidragssparr extends SakfragaIHandlaggningen
{
    @NotNull
    private UUID formanssparr;
    private GrundForBidragssparrutredning grundForBidragssparrutredning;
    private AnledningIngenBidragssparr anledningIngenBidragssparr;

    public Optional<GrundForBidragssparrutredning> getGrundForBidragssparrutredning()
    {
        return Optional.ofNullable( grundForBidragssparrutredning );
    }

    public Optional<AnledningIngenBidragssparr> getAnledningIngenBidragssparr()
    {
        return Optional.ofNullable( anledningIngenBidragssparr );
    }
}
