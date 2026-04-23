package se.fk.mimer.datamodel.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.organisation.Organisation;
import se.fk.mimer.datamodel.v1.referensdata.IDTyper;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class IDTyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private IDTyper idTyp;
    @NotNull
    @NotBlank
    private String varde;
    private Organisation avserOrganisation;

    public Optional<Organisation> getOrganisation()
    {
        return Optional.ofNullable( avserOrganisation );
    }
}
