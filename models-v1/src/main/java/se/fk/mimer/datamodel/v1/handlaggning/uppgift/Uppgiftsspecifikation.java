package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Verksamhetslogiktyper;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Behorighetsroller;
import se.fk.mimer.datamodel.v1.regel.Regel;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Uppgiftsspecifikation
{
    @NotNull
    private UUID id;
    @NotNull
    @Min(1)
    private int version;
    @NotNull
    @NotBlank
    private String namn;
    @NotNull
    @NotBlank
    private String uppgiftsbeskrivning;
    @NotNull
    private Verksamhetslogiktyper verksamhetslogik;
    @NotNull
    private Behorighetsroller behorighetsroll;
    @NotNull
    @NotBlank
    private String applikationsId;
    @NotNull
    @NotBlank
    private String guiURL;
    private Collection<Regel> avserRegler;

    public Optional<Collection<Regel>> getAvserRegler()
    {
        return Optional.ofNullable( avserRegler );
    }
}
