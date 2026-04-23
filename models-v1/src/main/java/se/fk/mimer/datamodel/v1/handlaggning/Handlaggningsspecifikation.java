package se.fk.mimer.datamodel.v1.handlaggning;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Uppgiftsspecifikation;

import java.util.Collection;
import java.util.Optional;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Handlaggningsspecifikation
{
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String bpmnUrl;
    @NotNull
    @NotBlank
    private String namn;
    @NotNull
    @NotBlank
    private String beskrivning;
    private Collection<Uppgiftsspecifikation> anvanderUppgiftsspecifikationer;

    public Optional<Collection<Uppgiftsspecifikation>> getAnvanderUppgiftsspecifikationer()
    {
        return Optional.ofNullable( anvanderUppgiftsspecifikationer );
    }
}
