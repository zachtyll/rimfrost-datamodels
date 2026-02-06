package se.fk.mimer.producermodels.v2.model.person;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
public class JuridiskPerson extends Person
{
    @Builder
    public JuridiskPerson( UUID id, String kundid, int revision, String variant, String organisationsnummer, Map<UUID, RollIKundbehov> rollIKundbehov)
    {
        super( id,  revision, variant, kundid, rollIKundbehov);
        this.organisationsnummer = organisationsnummer;
    }

    @Getter
    private String organisationsnummer;
}
