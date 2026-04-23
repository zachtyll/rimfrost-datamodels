package se.fk.mimer.datamodel.v1.tjanstgoringitotalforsvaret;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.tjanstgoringitotalforsvaret.TjanstgoringsStatustyper;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class TjanstgoringsstatusForTjanstgoringITotalforsvaret
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private TjanstgoringsStatustyper tjanstgoringsstatusTyp;
}
