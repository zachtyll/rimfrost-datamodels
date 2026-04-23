package se.fk.mimer.datamodel.v1.forman.sakfraga;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.forman.AnknytandeSakfragetyper;
import se.fk.mimer.datamodel.v1.referensdata.forman.BerattigadeSakfragetyper;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Sakfragetyper
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    private BerattigadeSakfragetyper rattenTillSakfraga;
    private AnknytandeSakfragetyper anknytandeSakfraga;

    public Optional<BerattigadeSakfragetyper> getBerattigadeSakfragetyper()
    {
        return Optional.ofNullable( rattenTillSakfraga );
    }

    public Optional<AnknytandeSakfragetyper> getAnknytandeSakfraga()
    {
        return Optional.ofNullable( anknytandeSakfraga );
    }
}
