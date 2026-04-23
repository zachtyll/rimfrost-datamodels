package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Tjansteanteckning extends Basuppgift
{
    @NotNull
    @NotBlank
    private String rubrik;
    @NotNull
    private String anteckning;
}
