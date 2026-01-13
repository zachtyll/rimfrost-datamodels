package se.fk.mimer.producermodels.bidragssparr;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.Period;
import se.fk.mimer.producermodels.ProduceratResultat;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class Bidragssparr extends ProduceratResultat
{
    public Bidragssparr( UUID id, UUID faststallsForKundbehov, int version, String avserPerson, Period period, String typ, String status,
                         AnledningIngenBidragssparr anledningIngenBidragssparr, BedomdMedvetenhet bedomdMedvetenhet, boolean bidragssparr,
                         BidragssparrStatus bidragssparrStatus, GrundForBeslut grundForBeslut, GrundForUtredning grundForUtredning)
    {
        super( id, faststallsForKundbehov, version, avserPerson, period, typ, status);
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
