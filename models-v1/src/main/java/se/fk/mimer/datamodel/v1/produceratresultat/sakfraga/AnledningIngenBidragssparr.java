package se.fk.mimer.datamodel.v1.produceratresultat.sakfraga;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

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
