package se.fk.mimer.datamodel.v1.forman.sakfraga;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.forman.Forman;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Sakfraga
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Sakfragetyper sakfragetyper;
    @NotNull
    private Sakfragetypkategori sakfragetypkategori;
    @NotNull
    @NotEmpty
    private Collection<Forman> ingarIFormaner;
    private String beskrivning;

    public Optional<Sakfragetyper> getSakfragetyper()
    {
        return Optional.ofNullable( sakfragetyper );
    }

    public Optional<String> getBeskrivning()
    {
        return Optional.ofNullable( beskrivning );
    }
}
