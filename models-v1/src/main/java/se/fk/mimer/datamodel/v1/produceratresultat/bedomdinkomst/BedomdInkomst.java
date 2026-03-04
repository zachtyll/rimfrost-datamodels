package se.fk.mimer.datamodel.v1.produceratresultat.bedomdinkomst;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.exceptions.DomainInvariantException;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.produceratresultat.Periodisering;
import se.fk.mimer.datamodel.v1.produceratresultat.ProduceratResultat;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class BedomdInkomst extends ProduceratResultat
{
    @Builder
    public BedomdInkomst( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period giltighetsperiod, String typ, String status,
                          double belopp, EInkomsttyp inkomsttyp, EInkomsttypKategori inkomsttypKategori, Periodisering periodisering, Beloppstyp beloppstyp )
    {
        super(id, revision, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
        this.belopp = belopp;
        this.inkomsttypKategori = inkomsttypKategori;
        this.setInkomsttyp( inkomsttyp );
        this.periodisering = periodisering;
        this.beloppstyp = beloppstyp;
    }

    private double belopp;

    private EInkomsttypKategori inkomsttypKategori;

    private EInkomsttyp inkomsttyp;

    private Periodisering periodisering;

    private Beloppstyp beloppstyp;

    private void setInkomsttyp( EInkomsttyp inkomsttyp)
    {
        if( inkomsttyp != null && inkomsttypKategori != null && inkomsttyp.getKategori() != inkomsttypKategori )
        {
            throw new DomainInvariantException( "Inkomsttypen " + inkomsttyp + " hör till kategorin " + inkomsttyp.getKategori() + ", inte " + inkomsttypKategori );
        }
        this.inkomsttyp = inkomsttyp;
    }
}
