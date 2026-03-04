package se.fk.mimer.datamodel.v2.yrkande;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;
import se.fk.mimer.datamodel.v2.beslut.Beslut;
import se.fk.mimer.datamodel.v2.beslut.delgivning.Delgivning;
import se.fk.mimer.datamodel.v2.person.Person;
import se.fk.mimer.datamodel.v2.produceratresultat.ProduceratResultat;
import se.fk.mimer.datamodel.v2.produkt.Erbjudande;
import se.fk.mimer.datamodel.v2.produkt.Produkt;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Builder
public class Yrkande
{
    @NotNull
    @Getter
    private UUID id;

    @Min(1)
    @Getter
    private int revision;

    private YrkandeStatus yrkandeStatus;
    private Avsikt avsikt;
    private String andringsorsak;
    private ZonedDateTime yrkandeDatum;
    private Period period;

    private Beslut beslut;
    private Delgivning delgivning;
    private Erbjudande avserErbjudande;
    private Produkt produkt;
    private List<Person> personer;
    private List<ProduceratResultat> produceradeResultat;

    @Getter
    private List<RollIYrkande> roller;

    public Optional<YrkandeStatus> getYrkandeStatus()
    {
        return Optional.ofNullable( yrkandeStatus );
    }

    public Optional<Avsikt> getAvsikt()
    {
        return Optional.ofNullable( avsikt );
    }

    public Optional<String> getAndringsorsak() { return Optional.ofNullable( andringsorsak ); }

    public Optional<ZonedDateTime> getyrkandeDatum() { return Optional.ofNullable( yrkandeDatum ); }

    public Optional<Period> getPeriod()
    {
        return Optional.ofNullable( period );
    }

    public Optional<Beslut> getBeslut() { return Optional.ofNullable( beslut ); }

    public Optional<Delgivning> getDelgivning() { return Optional.ofNullable( delgivning ); }

    public Optional<Erbjudande> getAvserErbjudande()
    {
        return Optional.ofNullable( avserErbjudande );
    }

    public Optional<Produkt> getProdukt() { return Optional.ofNullable( produkt ); }

    public Optional<List<Person>> getPersoner() { return Optional.ofNullable( personer ); }

    public Optional<List<ProduceratResultat>> getProduceradeResultat() { return Optional.ofNullable( produceradeResultat ); }

}
