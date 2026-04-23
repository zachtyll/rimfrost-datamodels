package se.fk.mimer.datamodel.v1.inkomst;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.inkomst.anstallning.Anstallning;
import se.fk.mimer.datamodel.v1.inkomst.ersattning.SpecificeradErsattningsperiod;

import java.util.Collection;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class InkomstAvTjanst extends Inkomst
{
    @NotNull
    private Collection<Anstallning> avserAnstallningar;
    @NotNull
    private Collection<SpecificeradErsattningsperiod> avserSpecificeradeErsattningsperioder;
}
