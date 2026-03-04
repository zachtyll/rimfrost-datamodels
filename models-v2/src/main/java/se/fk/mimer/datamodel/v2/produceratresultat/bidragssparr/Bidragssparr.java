package se.fk.mimer.datamodel.v2.produceratresultat.bidragssparr;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.person.Person;
import se.fk.mimer.datamodel.v2.produceratresultat.ProduceratResultat;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class Bidragssparr extends ProduceratResultat
{
    @Builder
    public Bidragssparr( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period period, String typ, String status,
                         AnledningIngenBidragssparr anledningIngenBidragssparr, BedomdMedvetenhet bedomdMedvetenhet, boolean bidragssparr,
                         BidragssparrStatus bidragssparrStatus, GrundForBeslut grundForBeslut, GrundForUtredning grundForUtredning)
    {
        super( id, revision, faststallsForKundbehov, avserPerson, period, typ, status);
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
