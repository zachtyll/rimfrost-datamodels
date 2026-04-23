package se.fk.mimer.datamodel.v1.handlaggning.uppgift.processbeskrivning;

import jakarta.validation.constraints.NotBlank;
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
class Aktivitet
{
    @NotNull
    @NotBlank
    private String aktivitetID;
    @NotNull
    @NotBlank
    private String namn;
    @NotNull
    private String beskrivning;
}
