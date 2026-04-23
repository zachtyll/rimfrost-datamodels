package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Informationsobjekt
{
    @NotNull
    private String id;
    @NotNull
    private int version;
}
