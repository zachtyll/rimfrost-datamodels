package se.fk.mimer.datamodel.v1.yrkande;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.Avsiktstyper;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Avsiktstyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Avsiktstyper avsiktstyp;
}
