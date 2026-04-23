package se.fk.mimer.datamodel.v1.person;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.organisation.JuridiskFormkod;
import se.fk.mimer.datamodel.v1.organisation.Organisation;
import se.fk.mimer.datamodel.v1.organisation.OrganisationsForm;
import se.fk.mimer.datamodel.v1.organisation.OrganisationsIdentitet;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Funktionar
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
    private OrganisationsIdentitet organisationsIdentitet;
    @NotNull
    private Organisation harFunktionIOrganisation;
    @NotNull
    private FysiskPerson arPerson;
}
