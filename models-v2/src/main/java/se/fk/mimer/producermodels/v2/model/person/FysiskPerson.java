package se.fk.mimer.producermodels.v2.model.person;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;
import se.fk.mimer.producermodels.v2.model.produkt.RollIProdukt;
import se.fk.mimer.producermodels.v2.validation.ValidFysiskPerson;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
@ValidFysiskPerson
public class FysiskPerson extends Person
{
    @Builder
    public FysiskPerson( UUID id, String kundid, int revision, String variant, @Nullable String personnummer, @Nullable RollIProdukt rollIProdukt,
                         @Nullable EnskildNaringsidkare enskildNaringsidkare, Map<UUID, RollIKundbehov> rollIKundbehov)
    {
        super( id, revision, variant, kundid, rollIKundbehov);
        this.personnummer = personnummer;
        this.rollIProdukt = rollIProdukt;
        this.enskildNaringsidkare = enskildNaringsidkare;
    }

    @Nullable
    private String personnummer;

    @Nullable
    private RollIProdukt rollIProdukt;

    @Nullable
    private EnskildNaringsidkare enskildNaringsidkare;

    public Optional<String> getPersonnummer()
    {
        return Optional.ofNullable( personnummer );
    }

    public Optional<RollIProdukt> getRollIProdukt(){ return Optional.ofNullable( rollIProdukt ); }

    public Optional<EnskildNaringsidkare> getEnskildNaringsidkare(){ return Optional.ofNullable( enskildNaringsidkare ); }
}
