package se.fk.mimer.datamodel.v2.produceratresultat.krav;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.produceratresultat.ersattning.beloppstyp.EBeloppstyp;
import se.fk.mimer.datamodel.v2.produceratresultat.ersattning.beloppstyp.EBeloppstypKategori;
import se.fk.mimer.datamodel.v2.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.datamodel.v2.person.Person;
import se.fk.mimer.datamodel.v2.produceratresultat.Periodisering;
import se.fk.mimer.datamodel.v2.produceratresultat.ProduceratResultat;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class Krav extends ProduceratResultat
{
    @Builder
    public Krav( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period giltighetsperiod, String typ, String status,
                 @Nullable EBeloppstypKategori beloppstypKategori, @Nullable EBeloppstyp beloppstyp, @Nullable Kravtyp kravtyp, @Nullable Periodisering periodisering,
                 @Nullable OmfattningBaseratPaErsattningstypEnligtLagrum omfattning )
    {
        super(id, revision, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
        this.beloppstypKategori = beloppstypKategori;
        this.setBeloppstyp( beloppstyp );
        this.kravtyp = kravtyp;
        this.periodisering = periodisering;
        this.omfattning = omfattning;
    }

    @Nullable
    private EBeloppstypKategori beloppstypKategori;

    @Nullable
    private EBeloppstyp beloppstyp;

    @Nullable
    private Kravtyp kravtyp;

    @Nullable
    private Periodisering periodisering;

    @Nullable
    private OmfattningBaseratPaErsattningstypEnligtLagrum omfattning;

//    //ToDo: Bryt ut dessa till egna helper klasser
    private void setBeloppstyp( EBeloppstyp beloppstyp )
    {
        if( beloppstyp != null && beloppstypKategori != null && beloppstyp.getBeloppstyp() != beloppstypKategori )
        {
            throw new IllegalArgumentException("Beloppstyp " + beloppstyp + "hör till typen " +beloppstyp.getBeloppstyp() + ", inte " + beloppstypKategori );
        }
        this.beloppstyp = beloppstyp;
    }
}
