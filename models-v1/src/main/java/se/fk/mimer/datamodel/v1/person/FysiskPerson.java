package se.fk.mimer.datamodel.v1.person;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.yrkande.RollIYrkande;
import se.fk.mimer.datamodel.v1.produkt.RollIProdukt;
import se.fk.mimer.datamodel.v1.validation.ValidFysiskPerson;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@ValidFysiskPerson
public class FysiskPerson extends Person
{
    @Builder
    public FysiskPerson( UUID id, String kundid, int revision, String personnummer, RollIProdukt rollIProdukt,
                         EnskildNaringsidkare enskildNaringsidkare, Map<UUID, RollIYrkande> rollIKundbehov)
    {
        super( id, revision, kundid, rollIKundbehov);
        this.personnummer = personnummer;
        this.rollIProdukt = rollIProdukt;
        this.enskildNaringsidkare = enskildNaringsidkare;
    }

    private String personnummer;

    private RollIProdukt rollIProdukt;

    private EnskildNaringsidkare enskildNaringsidkare;

    public Optional<String> getPersonnummer()
    {
        return Optional.ofNullable( personnummer );
    }

    public Optional<RollIProdukt> getRollIProdukt(){ return Optional.ofNullable( rollIProdukt ); }

    public Optional<EnskildNaringsidkare> getEnskildNaringsidkare(){ return Optional.ofNullable( enskildNaringsidkare ); }
}
