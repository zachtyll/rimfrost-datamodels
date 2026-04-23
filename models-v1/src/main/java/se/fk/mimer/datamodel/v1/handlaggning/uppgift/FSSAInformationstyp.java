package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.handlaggning.FSSAInformationstyper;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FSSAInformationstyp
{
    @NotNull
    private UUID id;
    @NotNull
    @Min(1)
    private int version;
    @NotNull
    private FSSAInformationstyper fssaInfo;
}
