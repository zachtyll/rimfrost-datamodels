package se.fk.mimer.datamodel.v2.person;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.yrkande.RollIYrkande;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
public class JuridiskPerson extends Person
{
    @Builder
    public JuridiskPerson( UUID id, String kundid, int revision, String organisationsnummer, Map<UUID, RollIYrkande> rollIKundbehov)
    {
        super( id,  revision, kundid, rollIKundbehov);
        this.organisationsnummer = organisationsnummer;
    }

    @Getter
    private String organisationsnummer;
}
