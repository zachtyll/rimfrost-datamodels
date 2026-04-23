package se.fk.mimer.datamodel.v1;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.Verksamhetslogik;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Verksamhetslogiktyper
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Verksamhetslogik verksamhetslogik;
}
