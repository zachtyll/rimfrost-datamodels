package se.fk.mimer.datamodel.v1.inkomst.ersattning;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.inkomst.anstallning.Anstallning;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class SpecificeradErsattningsperiod
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private ZonedDateTime from;
    private ZonedDateTime tom;
    @NotNull
    private Double ersattningsSummaForPeriod;
    private UtbetaldAllmanErsattning gallerForUtbetaldAllmanErsattning;
    private Anstallning gallerForAnstallning;

    public Optional<ZonedDateTime> getTom()
    {
        return Optional.ofNullable( tom );
    }
    public Optional<UtbetaldAllmanErsattning> getGallerForUtbetaldAllmanErsattning()
    {
        return Optional.ofNullable( gallerForUtbetaldAllmanErsattning );
    }
    public Optional<Anstallning> getGallerForAnstallning()
    {
        return Optional.ofNullable( gallerForAnstallning );
    }
}
