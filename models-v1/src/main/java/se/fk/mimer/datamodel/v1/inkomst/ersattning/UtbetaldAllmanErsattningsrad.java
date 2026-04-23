package se.fk.mimer.datamodel.v1.inkomst.ersattning;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class UtbetaldAllmanErsattningsrad
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Ersattningstyp ersattningstyp;
    @NotNull
    private ZonedDateTime from;
    private ZonedDateTime tom;
    @NotNull
    private Double belopp;
    @NotNull
    private UtbetaldAllmanErsattning tillhorUtbetaldAllmanErsattning;

    public Optional<ZonedDateTime> getTom()
    {
        return Optional.ofNullable( tom );
    }
}
