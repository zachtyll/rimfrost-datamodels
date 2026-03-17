package se.fk.mimer.datamodel.v1.produceratresultat.ersattning;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.Beloppstyp;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.BeloppstypKategori;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.Berakningsgrund;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning.BerakningsgrundRegel;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.Periodisering;
import se.fk.mimer.datamodel.v1.produceratresultat.ProduceratResultat;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode ( callSuper = true )
@Setter
@Getter
public class Ersattning extends ProduceratResultat
{
    @Builder
    public Ersattning( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period giltighetsperiod, String typ, String status,
                       double belopp, @Nullable BerakningsgrundRegel berakningsgrundsLagrum, Berakningsgrund berakningsgrund, @Nullable BeloppstypKategori beloppstypKategori,
                       Beloppstyp beloppstyp, @Nullable OmfattningBaseratPaErsattningstypEnligtLagrum omfattning,
                       @Nullable ErsattningstypEnligtLagrum ersattningstypEnligtLagrum, @Nullable Periodisering periodisering, Ersattning[] samordnasMedErsattning,
                       @Nullable String avslagsanledning )
    {
        super(id, revision, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
        this.belopp = belopp;
        this.berakningsgrundsLagrum = berakningsgrundsLagrum;
        this.setBerakningsgrund( berakningsgrund );
        this.beloppstypKategori = beloppstypKategori;
        this.setBeloppstyp( beloppstyp );
        this.omfattning = omfattning;
        this.ersattningstypEnligtLagrum = ersattningstypEnligtLagrum;
        this.periodisering = periodisering;
        this.samordnasMedErsattning = samordnasMedErsattning;
        this.avslagsanledning = avslagsanledning;

    }

    @NotNull( message = "Ersattning must have a Belopp set.")
    private double belopp;

    @Nullable
    private BerakningsgrundRegel berakningsgrundsLagrum;

    @Nullable
    private Berakningsgrund berakningsgrund;

    @Nullable
    private BeloppstypKategori beloppstypKategori;

    @Nullable
    private Beloppstyp beloppstyp;

    @Nullable
    private OmfattningBaseratPaErsattningstypEnligtLagrum omfattning;

    @Nullable
    private ErsattningstypEnligtLagrum ersattningstypEnligtLagrum;

    @Nullable
    private Periodisering periodisering;

    @NotNull( message = "SamordnasMedErsattning cannot be null. If none exist, leave it empty.")
    private Ersattning[] samordnasMedErsattning;

    @Nullable
    private String avslagsanledning;

    //ToDo: Bryt ut dessa till egna helper klasser
    private void setBerakningsgrund( Berakningsgrund berakningsgrund )
    {
        if( berakningsgrund != null && berakningsgrundsLagrum != null && berakningsgrund.getRegel() != berakningsgrundsLagrum)
        {
            throw new IllegalArgumentException("Beräkningsgrund " + berakningsgrund + "hör till lagrum " + berakningsgrund.getRegel() + ", inte " + berakningsgrundsLagrum );
        }
        this.berakningsgrund = berakningsgrund;
    }

    //ToDo: Bryt ut dessa till egna helper klasser
    private void setBeloppstyp( Beloppstyp beloppstyp )
    {
        if( beloppstyp != null && beloppstypKategori!= null && beloppstyp.getBeloppstyp() != beloppstypKategori )
        {
            throw new IllegalArgumentException("Beloppstyp " + beloppstyp + "hör till typen " +beloppstyp.getBeloppstyp() + ", inte " + beloppstypKategori );
        }
        this.beloppstyp = beloppstyp;
    }
}
