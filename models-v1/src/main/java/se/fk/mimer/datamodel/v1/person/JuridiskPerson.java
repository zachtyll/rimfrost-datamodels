package se.fk.mimer.datamodel.v1.person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.yrkande.RollIYrkande;

import java.util.Map;
import java.util.UUID;

@Jacksonized
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@SuperBuilder
public class JuridiskPerson extends Person
{
    public JuridiskPerson( UUID id, String kundid, int revision, String organisationsnummer, Map<UUID, RollIYrkande> rollIKundbehov)
    {
        super( id,  revision, kundid, rollIKundbehov);
        this.organisationsnummer = organisationsnummer;
    }

    @Getter
    private String organisationsnummer;
}
