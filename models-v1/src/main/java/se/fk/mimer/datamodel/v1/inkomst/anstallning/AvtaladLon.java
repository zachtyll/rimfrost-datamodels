package se.fk.mimer.datamodel.v1.inkomst.anstallning;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class AvtaladLon
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Anstallning gallerForAnstallning;
}
