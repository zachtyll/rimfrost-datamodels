package se.fk.mimer.datamodel.v1.produceratresultat.periodtillampliglagstiftning;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.Artikel;
import se.fk.mimer.datamodel.v1.referensdata.produceratresultat.Forordning;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.YrkandeStatus;
import se.fk.mimer.datamodel.v1.referensdata.Land;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.produceratresultat.ProduceratResultat;
import se.fk.rdl.utils.errorhandling.exceptions.MimerException;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class PeriodTillampligLagstiftning extends ProduceratResultat
{
    @Builder
    public PeriodTillampligLagstiftning( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period giltighetsperiod, String typ, String status,
                                         @Nullable Forordning forordning, Artikel artikel, @Nullable Land bosattning,
                                         @Nullable String statVarsSocialforsakringPersonenOmfattasAv, @Nullable YrkandeStatus yrkandeStatus )
    {
        super( id, revision, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
        this.forordning = forordning;
        this.setArtikel(artikel);
        this.bosattning = bosattning;
        this.statVarsSocialforsakringPersonenOmfattasAv = statVarsSocialforsakringPersonenOmfattasAv;
        this.yrkandeStatus = yrkandeStatus;

    }

    @Nullable
    private Forordning forordning;

    @Nullable
    private Artikel artikel;

    @Nullable
    private Land bosattning;

    @Nullable
    private String statVarsSocialforsakringPersonenOmfattasAv;

    @Nullable
    private YrkandeStatus yrkandeStatus;

    private void setArtikel( Artikel artikel)
    {
        if( artikel != null && forordning != null && artikel.getForordning() != forordning )
        {
            throw new MimerException("Artikel " + artikel + " hör till förordning " + artikel.getForordning() + ", inte " + forordning );
        }
        this.artikel = artikel;
    }
}
