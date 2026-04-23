package se.fk.mimer.datamodel.v1.inkomst.ersattning;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.inkomst.Ersattningsradtyper;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class ErsattningsradTyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Ersattningsradtyper ersattningsradTyp;
}
