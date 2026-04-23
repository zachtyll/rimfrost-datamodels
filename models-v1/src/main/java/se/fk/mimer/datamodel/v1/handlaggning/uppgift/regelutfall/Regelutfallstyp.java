package se.fk.mimer.datamodel.v1.handlaggning.uppgift.regelutfall;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.handlaggning.Regelutfallstyper;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Regelutfallstyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Regelutfallstyper regelutfall;
}
