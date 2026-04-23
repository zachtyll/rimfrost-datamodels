package se.fk.mimer.datamodel.v1.organisation;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.organisation.OrganisationsIdentitetsKoder;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class OrganisationsIdentitet
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private OrganisationsIdentitetsKoder kod;
}
