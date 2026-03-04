package se.fk.mimer.datamodel.v2.person.foretradare;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.yrkande.RollIYrkande;
import se.fk.mimer.datamodel.v2.person.EnskildNaringsidkare;
import se.fk.mimer.datamodel.v2.person.FysiskPerson;
import se.fk.mimer.datamodel.v2.person.Person;
import se.fk.mimer.datamodel.v2.produkt.RollIProdukt;
import se.fk.mimer.datamodel.v2.validation.ValidFysiskPerson;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
@ValidFysiskPerson
public class Ombud extends FysiskPerson
{
    public Ombud( UUID id, String kundid, int revision, Map<UUID, RollIYrkande> rollIKundbehov, String personnummer, RollIProdukt rollIProdukt,
                  EnskildNaringsidkare enskildNaringsidkare, String personId, UUID ombudId, @Nullable Foretradartyp foretradartyp, @Nullable Period period,
                  @Nullable Person foretrader)
    {
        super(id, kundid, revision, personnummer, rollIProdukt, enskildNaringsidkare,  rollIKundbehov);
        this.personId = personId;
        this.ombudId = ombudId;
        this.foretradartyp = foretradartyp;
        this.period = period;
        this.foretrader = foretrader;
    }

    @NotNull (message = "Ombud must have a personId.")
    private String personId;

    @NotNull (message = "Ombud must have a proper ID.")
    private UUID ombudId;

    @Nullable
    private Foretradartyp foretradartyp;

    @Nullable
    private Period period;

    @Nullable
    private Person foretrader;
}
