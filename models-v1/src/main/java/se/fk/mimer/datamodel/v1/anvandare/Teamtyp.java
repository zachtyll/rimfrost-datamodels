package se.fk.mimer.datamodel.v1.anvandare;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.anvandare.Teamtyper;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Teamtyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Teamtyper teamtyp;
}
