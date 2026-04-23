package se.fk.mimer.datamodel.v1.organisation;

import jakarta.validation.constraints.NotBlank;
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
public class Organisationsenhet
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    @NotBlank
    private String namn;
    @NotNull
    @NotBlank
    private String kod;
    private Organisationsenhet tillhorOrganisationsenhet;
    @NotNull
    private Organisation tillhorOrganisation;

    Optional<Organisationsenhet> getTillhorOrganisationsenhet()
    {
        return Optional.ofNullable( tillhorOrganisationsenhet );
    }
}
