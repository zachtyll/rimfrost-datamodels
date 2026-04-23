package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.sakfraga.stallningstagande.BedomdInkomst;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class StallningstagnaInkomsttyper
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private BedomdInkomst bedomdInkomst;
}
