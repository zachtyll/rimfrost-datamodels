package se.fk.mimer.datamodel.v1.produkt.forman;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.AnknytandeSakfragetyper;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.BerattigadeSakfragetyper;

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
