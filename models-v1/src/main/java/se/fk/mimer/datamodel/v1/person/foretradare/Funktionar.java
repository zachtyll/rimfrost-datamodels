package se.fk.mimer.datamodel.v1.person.foretradare;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.referensdata.person.Foretradartyp;
import se.fk.mimer.datamodel.v1.referensdata.person.Funktionarstyp;
import se.fk.mimer.datamodel.v1.yrkande.RollIYrkande;
import se.fk.mimer.datamodel.v1.person.EnskildNaringsidkare;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.produkt.RollIProdukt;
import se.fk.mimer.datamodel.v1.validation.ValidFysiskPerson;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
@ValidFysiskPerson
public class Funktionar extends FysiskPerson
{
    public Funktionar( UUID id, String kundid, int revision, Map<UUID, RollIYrkande> rollIKundbehov, String personnummer, RollIProdukt rollIProdukt,
                       EnskildNaringsidkare enskildNaringsidkare, String personId, UUID funktionarId, Foretradartyp foretradartyp, Period period,
                       Person foretrader, Funktionarstyp funktionarstyp)
    {
        super(id, kundid, revision, personnummer, rollIProdukt, enskildNaringsidkare,  rollIKundbehov);
        this.personId = personId;
        this.funktionarId = funktionarId;
        this.foretradartyp = foretradartyp;
        this.period = period;
        this.foretrader = foretrader;
        this.funktionarstyp = funktionarstyp;
    }

    @NotNull( message = "A funktionar needs a personId." )
    private String personId;

    @NotNull( message = "A Funktionar needs a proper ID.")
    private UUID funktionarId;

    @Nullable
    private Foretradartyp foretradartyp;

    @Nullable
    private Period period;

    @Nullable
    private Person foretrader;

    @Nullable
    private Funktionarstyp funktionarstyp;
}
