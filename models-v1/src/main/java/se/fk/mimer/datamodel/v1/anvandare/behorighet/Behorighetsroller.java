package se.fk.mimer.datamodel.v1.anvandare.behorighet;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Behorighetsroller
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private se.fk.mimer.datamodel.v1.referensdata.anvandare.behorighet.Behorighetsroller behorighetsroll;
    private Behorighetsgrupper tillhorBehorighetsgrupp;

    public Optional<Behorighetsgrupper> getTillhorBehorighetsgrupp()
    {
        return Optional.ofNullable( tillhorBehorighetsgrupp );
    }
}
