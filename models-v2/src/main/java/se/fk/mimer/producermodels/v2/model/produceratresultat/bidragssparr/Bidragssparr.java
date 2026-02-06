package se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ProduceratResultat;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class Bidragssparr extends ProduceratResultat
{
    @Builder
    public Bidragssparr( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period period, String typ, String status,
                         AnledningIngenBidragssparr anledningIngenBidragssparr, BedomdMedvetenhet bedomdMedvetenhet, boolean bidragssparr,
                         BidragssparrStatus bidragssparrStatus, GrundForBeslut grundForBeslut, GrundForUtredning grundForUtredning)
    {
        super( id, revision, variant, faststallsForKundbehov, avserPerson, period, typ, status);
        this.anledningIngenBidragssparr = anledningIngenBidragssparr;
        this.bedomdMedvetenhet = bedomdMedvetenhet;
        this.bidragssparr = bidragssparr;
        this.bidragssparrStatus = bidragssparrStatus;
        this.grundForBeslut = grundForBeslut;
        this.grundForUtredning = grundForUtredning;
    }

    private AnledningIngenBidragssparr anledningIngenBidragssparr;

    private BedomdMedvetenhet bedomdMedvetenhet;

    private boolean bidragssparr;

    private BidragssparrStatus bidragssparrStatus;

    private GrundForBeslut grundForBeslut;

    private GrundForUtredning grundForUtredning;
}
