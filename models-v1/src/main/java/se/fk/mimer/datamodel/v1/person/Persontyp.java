package se.fk.mimer.datamodel.v1.person;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.organisation.Organisation;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Persontyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private IDTyp idTyp;
    @NotNull
    private Individ arEnIndivid;
    private Organisation juridiskPersonForOrganisation;
    private FysiskPerson fysiskPerson;

    public Optional<Organisation> getJuridiskPersonForOrganisation()
    {
        return Optional.ofNullable( juridiskPersonForOrganisation );
    }

    public Optional<FysiskPerson> getFysiskPerson()
    {
        return Optional.ofNullable( fysiskPerson );
    }
}
