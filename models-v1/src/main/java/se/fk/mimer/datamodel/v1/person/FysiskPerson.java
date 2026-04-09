package se.fk.mimer.datamodel.v1.person;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.yrkande.RollIYrkande;
import se.fk.mimer.datamodel.v1.validation.ValidFysiskPerson;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@ValidFysiskPerson
@SuperBuilder
public class FysiskPerson extends Person
{
    public FysiskPerson( UUID id, String kundid, int revision, String personnummer,
                         EnskildNaringsidkare enskildNaringsidkare, Map<UUID, RollIYrkande> rollIKundbehov)
    {
        super( id, revision, kundid, rollIKundbehov);
        this.personnummer = personnummer;
        this.enskildNaringsidkare = enskildNaringsidkare;
    }

    private String personnummer;

    private EnskildNaringsidkare enskildNaringsidkare;

    public Optional<String> getPersonnummer()
    {
        return Optional.ofNullable( personnummer );
    }

    public Optional<EnskildNaringsidkare> getEnskildNaringsidkare(){ return Optional.ofNullable( enskildNaringsidkare ); }
}
