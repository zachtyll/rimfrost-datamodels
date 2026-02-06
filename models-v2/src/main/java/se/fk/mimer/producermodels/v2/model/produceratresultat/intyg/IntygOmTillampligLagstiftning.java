package se.fk.mimer.producermodels.v2.model.produceratresultat.intyg;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.person.Person;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@Getter
public class IntygOmTillampligLagstiftning extends Intyg
{
    @Builder
    public IntygOmTillampligLagstiftning( UUID id, UUID faststallsForKundbehov, int revision, String variant, Person avserPerson, Period giltighetsperiod, String typ, String status,
                                          String institution, String beskrivning, ZonedDateTime utfardatDatum, @Nullable UtlandsktForetag utlandsktForetag, @Nullable Intygstyp intygstyp,
                                          boolean intygetGallerHelaVersamhetsperioden, boolean beslutetArProvisoriskt, boolean verksamhetSomEgenforetagare, boolean ingenFastAdressDarVerksamhetenSkaBedrivas)
    {
        super( id, faststallsForKundbehov, revision, variant, avserPerson, giltighetsperiod, typ, status, institution, beskrivning, utfardatDatum);
        this.utlandsktForetag = utlandsktForetag;
        this.intygstyp = intygstyp;
        this.intygetGallerHelaVersamhetsperioden = intygetGallerHelaVersamhetsperioden;
        this.beslutetArProvisoriskt = beslutetArProvisoriskt;
        this.verksamhetSomEgenforetagare = verksamhetSomEgenforetagare;
        this.ingenFastAdressDarVerksamhetenSkaBedrivas = ingenFastAdressDarVerksamhetenSkaBedrivas;
    }

    @Nullable
    private UtlandsktForetag utlandsktForetag;

    @Nullable
    private Intygstyp intygstyp;

    @NotNull
    private boolean intygetGallerHelaVersamhetsperioden;

    @NotNull
    private boolean beslutetArProvisoriskt;

    @NotNull
    private boolean verksamhetSomEgenforetagare;

    @NotNull
    private boolean ingenFastAdressDarVerksamhetenSkaBedrivas;
}
