package se.fk.mimer.datamodel.v1.organisation;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Organisation
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private OrganisationsForm organisationsFormkod;
    @NotNull
    private JuridiskFormkod juridiskFormkod;
    @NotNull
    private OrganisationsIdentitet organisationsIdentitetskod;
}
