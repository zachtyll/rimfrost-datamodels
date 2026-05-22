package se.fk.mimer.datamodel.v1.sakfragastallningstagande;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.person.Persontyp;
import se.fk.mimer.datamodel.v1.regel.Regel;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.Betalningsbelopp;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.SjukpenninggrundandeInkomst;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.medanknytningtillformaner.bidragssparr.bidragssparr.Bidragssparr;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill.RattenTillPeriod;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill.ersattning.Ersattning;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenBosattningOchEllerArbeteISverige;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenInkomst;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenKostnad;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden.StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner;
import se.fk.mimer.datamodel.v1.yrkande.Yrkandestatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Setter
@Getter
@SuperBuilder
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = Ersattning.class, name = "ersattning" ),
        @JsonSubTypes.Type( value = RattenTillPeriod.class, name = "rattenTillPeriod" ),
        @JsonSubTypes.Type( value = Bidragssparr.class, name = "bidragssparr" ),
        @JsonSubTypes.Type( value = Betalningsbelopp.class, name = "betalningsbelopp" ),
        @JsonSubTypes.Type( value = SjukpenninggrundandeInkomst.class, name = "sjukpenninggrundandeInkomst" ),
        @JsonSubTypes.Type( value = StallningstagenKostnad.class, name = "stallningstagandenKostnad" ),
        @JsonSubTypes.Type( value = StallningstagenInkomst.class, name = "stallningstagenInkomst" ),
        @JsonSubTypes.Type( value = StallningstagenBosattningOchEllerArbeteISverige.class, name =
                "stallningstagenBosattningOchEllerArbeteISverige" ),
        @JsonSubTypes.Type( value =
                StallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner.class,
                name = "stallningstagenKvalificeringEllerUndantagenKvalificeringForVissaBosattningsbaseradeFormaner" )
} )
public abstract class SakfragaStallningstagande
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private ZonedDateTime from;
    private ZonedDateTime tom;
    @NotNull
    private Yrkandestatus yrkandestatus;
    @NotNull
    private Collection<Persontyp> avserPersontyper;
    @NotNull
    private UUID faststallsForYrkande;
    @NotNull
    private Collection<Regel> godkandRegler;
    @NotNull
    private Collection<Regel> avslagPaGrundAvRegler;

    public Optional<ZonedDateTime> getTom()
    {
        return Optional.ofNullable( tom );
    }
}
