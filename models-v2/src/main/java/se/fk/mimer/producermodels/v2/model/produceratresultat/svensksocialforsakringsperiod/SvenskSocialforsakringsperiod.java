package se.fk.mimer.producermodels.v2.model.produceratresultat.svensksocialforsakringsperiod;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsstatus;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.lagrum.Lagrum;
import se.fk.mimer.producermodels.v2.model.person.Person;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ProduceratResultat;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class SvenskSocialforsakringsperiod extends ProduceratResultat
{
    @Builder
    public SvenskSocialforsakringsperiod( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period period, String typ,
                                          String status, @Nullable Socialforsakringsgrund socialforsakringsgrund, @Nullable Lagrum lagrum,
                                          @Nullable Kundbehovsstatus kundbehovsstatus )
    {
        super( id, revision, variant, faststallsForKundbehov, avserPerson, period, typ, status );
        this.socialforsakringsgrund = socialforsakringsgrund;
        this.lagrum = lagrum;
        this.kundbehovsstatus = kundbehovsstatus;
    }

    @Nullable
    private Socialforsakringsgrund socialforsakringsgrund;

    @Nullable
    private Lagrum lagrum;

    @Nullable
    private Kundbehovsstatus kundbehovsstatus;
}
