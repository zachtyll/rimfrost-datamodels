package se.fk.mimer.datamodel.v1.yrkande.beslut;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.beslut.Versionsbeslutstyper;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Beslutstyp
{
    @NotNull
    private UUID id;
    @NotNull
    private Versionsbeslutstyper versionsbesluttyp;
}
