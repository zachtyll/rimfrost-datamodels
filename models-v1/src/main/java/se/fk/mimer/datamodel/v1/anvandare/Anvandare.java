package se.fk.mimer.datamodel.v1.anvandare;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Behorighetsgrupper;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Behorighetsroller;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Yrkesroll;
import se.fk.mimer.datamodel.v1.organisation.Organisationsenhet;

import java.util.Collection;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Anvandare
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private IDTyp idTyp;
    @NotNull
    private Collection<ErbjudnaDatamangder> harErbjudnaDatamangder;
    @NotNull
    private Collection<ErbjudanDatamangdsgrupper> tillhorErbjudanDatamangdsgrupper;
    @NotNull
    private Collection<Behorighetsroller> harBehorighetsroller;
    @NotNull
    private Collection<Behorighetsgrupper> tillhorBehorighetsgrupper;
    @NotNull
    private Organisationsenhet avserOrganisationsenhet;
    @NotNull
    private Yrkesroll harYrkesroll;
    @NotNull
    private Collection<Team> tillhorTeam;
}
