package se.fk.mimer.datamodel.v1.anvandare;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.organisation.Organisationsenhet;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Team
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    private String namn;
    @NotNull
    private Teamtyp teamtyp;
    @NotNull
    private Organisationsenhet tillhorOrganisationsenhet;
    @NotNull
    private Anvandare kontaktAnvandare;

    public Optional<String> getNamn()
    {
        return Optional.ofNullable( namn );
    }
}
