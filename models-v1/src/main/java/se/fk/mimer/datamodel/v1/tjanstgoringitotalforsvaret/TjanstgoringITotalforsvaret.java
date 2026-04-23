package se.fk.mimer.datamodel.v1.tjanstgoringitotalforsvaret;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.organisation.Organisationsenhet;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class TjanstgoringITotalforsvaret
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private FysiskPerson avserPerson;
    private TjanstgoringITotalforsvaret tjanstgoringITotalforsvaret;
    private PlikttypForTjanstgoringITotalforsvaret plikttypForTjanstgoringITotalforsvaret;
    private TjanstgoringsstatusForTjanstgoringITotalforsvaret tjanstgoringsstatusForTjanstgoringITotalforsvaret;
    @NotNull
    private ZonedDateTime tjanstgoringFrom;
    @NotNull
    private ZonedDateTime tjanstgoringTom;
    @NotNull
    private Organisationsenhet avserOrganisationsenhet;

    public Optional<TjanstgoringITotalforsvaret> getTjanstgoringITotalforsvaret()
    {
        return Optional.ofNullable( tjanstgoringITotalforsvaret );
    }

    public Optional<PlikttypForTjanstgoringITotalforsvaret> getPlikttypForTjanstgoringITotalforsvaret()
    {
        return Optional.ofNullable( plikttypForTjanstgoringITotalforsvaret );
    }

    public Optional<TjanstgoringsstatusForTjanstgoringITotalforsvaret> getTjanstgoringsstatusForTjansgoringITotalforsvaret()
    {
        return Optional.ofNullable( tjanstgoringsstatusForTjanstgoringITotalforsvaret );
    }
}
