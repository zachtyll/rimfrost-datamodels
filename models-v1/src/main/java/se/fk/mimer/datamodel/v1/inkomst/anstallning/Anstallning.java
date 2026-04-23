package se.fk.mimer.datamodel.v1.inkomst.anstallning;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.anvandare.behorighet.Yrkesroll;
import se.fk.mimer.datamodel.v1.organisation.Organisation;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Anstallning
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private int anstallninggradProcent;
    @NotNull
    private ZonedDateTime forstaAnstallningsdag;
    private ZonedDateTime sistaAnstallningsdag;
    @NotNull
    private Organisation harAvtalMedOrganisation;
    @NotNull
    private FysiskPerson avserFysiskPerson;
    @NotNull
    private Yrkesroll avserRoll;

    public Optional<ZonedDateTime> getSistaAnstallningsdag()
    {
        return Optional.ofNullable( sistaAnstallningsdag );
    }
}
