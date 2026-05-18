package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class StallningstagenBosattningOchEllerArbeteISverige extends StallningstagandeIHandlaggningen
{
    @NotNull
    private Boolean bosatt;
    @NotNull
    private Boolean arbetar;
}
