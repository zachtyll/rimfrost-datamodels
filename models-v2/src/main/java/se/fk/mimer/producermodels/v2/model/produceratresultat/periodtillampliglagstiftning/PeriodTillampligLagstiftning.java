package se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.exceptions.MimerException;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsstatus;
import se.fk.mimer.producermodels.v2.model.Land;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ProduceratResultat;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class PeriodTillampligLagstiftning extends ProduceratResultat
{
    @Builder
    public PeriodTillampligLagstiftning( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period giltighetsperiod, String typ, String status,
                                         @Nullable EForordning forordning, EArtikel artikel, @Nullable Land bosattning,
                                         @Nullable String statVarsSocialforsakringPersonenOmfattasAv, @Nullable Kundbehovsstatus kundbehovsstatus )
    {
        super( id, revision, variant, faststallsForKundbehov, avserPerson, giltighetsperiod, typ, status);
        this.forordning = forordning;
        this.setArtikel(artikel);
        this.bosattning = bosattning;
        this.statVarsSocialforsakringPersonenOmfattasAv = statVarsSocialforsakringPersonenOmfattasAv;
        this.kundbehovsstatus = kundbehovsstatus;

    }

    @Nullable
    private EForordning forordning;

    @Nullable
    private EArtikel artikel;

    @Nullable
    private Land bosattning;

    @Nullable
    private String statVarsSocialforsakringPersonenOmfattasAv;

    @Nullable
    private Kundbehovsstatus kundbehovsstatus;

    private void setArtikel(EArtikel artikel)
    {
        if( artikel != null && forordning != null && artikel.getForordning() != forordning )
        {
            throw new MimerException("Artikel " + artikel + " hör till förordning " + artikel.getForordning() + ", inte " + forordning );
        }
        this.artikel = artikel;
    }
}
