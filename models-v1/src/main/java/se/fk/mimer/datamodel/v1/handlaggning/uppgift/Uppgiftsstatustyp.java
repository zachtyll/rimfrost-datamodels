package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.Uppgiftsstatus;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Uppgiftsstatustyp
{
    @NotNull
    private UUID id;
    @NotNull
    @Min( 1 )
    private int version;
    @NotNull
    private Uppgiftsstatus uppgiftsstatus;
}
