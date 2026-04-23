package se.fk.mimer.datamodel.v1.yrkande.beslut;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.beslut.Avslutstyper;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Avslutstyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Avslutstyper avslutstyp;
}
