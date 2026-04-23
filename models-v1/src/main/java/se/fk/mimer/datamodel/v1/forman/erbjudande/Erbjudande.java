package se.fk.mimer.datamodel.v1.forman.erbjudande;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.forman.Forman;

import java.util.Collection;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Erbjudande
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Erbjudandetyp erbjudande;
    @NotNull
    @NotEmpty
    private Collection<Forman> ingarIForman;
    @NotNull
    @NotBlank
    private String beskrivning;
}
