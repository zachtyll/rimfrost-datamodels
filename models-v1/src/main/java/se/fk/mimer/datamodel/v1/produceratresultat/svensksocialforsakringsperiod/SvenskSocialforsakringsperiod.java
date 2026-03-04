package se.fk.mimer.datamodel.v1.produceratresultat.svensksocialforsakringsperiod;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.yrkande.YrkandeStatus;
import se.fk.mimer.datamodel.v1.Period;
import se.fk.mimer.datamodel.v1.lagrum.Lagrum;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.produceratresultat.ProduceratResultat;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class SvenskSocialforsakringsperiod extends ProduceratResultat
{
    @Builder
    public SvenskSocialforsakringsperiod( UUID id, UUID faststallsForKundbehov, int revision, Person avserPerson, Period period, String typ,
                                          String status, @Nullable Socialforsakringsgrund socialforsakringsgrund, @Nullable Lagrum lagrum,
                                          @Nullable YrkandeStatus yrkandeStatus )
    {
        super( id, revision, faststallsForKundbehov, avserPerson, period, typ, status );
        this.socialforsakringsgrund = socialforsakringsgrund;
        this.lagrum = lagrum;
        this.yrkandeStatus = yrkandeStatus;
    }

    @Nullable
    private Socialforsakringsgrund socialforsakringsgrund;

    @Nullable
    private Lagrum lagrum;

    @Nullable
    private YrkandeStatus yrkandeStatus;
}
