package se.fk.mimer.producermodels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.deserializers.ZonedDateTimeDeserializer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;


@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
public class Ersattning extends ProduceratResultat
{
    @Builder
    public Ersattning( UUID id, UUID faststallsForKundbehov, int version, String avserPerson, Period period, String typ, ZonedDateTime datum, String beloppstyp, BigDecimal belopp, double omfattning, String periodisering, @Nullable String andringsorsak, @Nullable String avslagsanledning, String status, String berakningsgrund )
    {
        super( id, faststallsForKundbehov, version, avserPerson, period, typ, status );
        this.datum = datum;
        this.beloppstyp = beloppstyp;
        this.belopp = belopp;
        this.omfattning = omfattning;
        this.periodisering = periodisering;
        this.andringsorsak = andringsorsak;
        this.avslagsanledning = avslagsanledning;
        this.berakningsgrund = berakningsgrund;
    }

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    @NotNull( message = "Ersattning must have a datum" )
    private ZonedDateTime datum;

    @NotBlank( message = "Ersattning must have a beloppstyp" )
    private String beloppstyp;

    @NotNull( message = "Ersattning must have a belopp" )
    private BigDecimal belopp;

    @NotNull( message = "Ersattning must have an omfattning" )
    @Getter
    private double omfattning;

    @NotBlank( message = "Ersattning must have a periodisering" )
    private String periodisering;

    @Nullable
    private String andringsorsak;

    @Nullable
    private String avslagsanledning;

    @NotBlank( message = "Ersattning must have a berakningsgrund" )
    private String berakningsgrund;

    public Optional<ZonedDateTime> getDatum()
    {
        return Optional.ofNullable( datum );
    }

    public Optional<String> getBeloppstyp()
    {
        return Optional.ofNullable( beloppstyp );
    }

    public Optional<BigDecimal> getBelopp()
    {
        return Optional.ofNullable( belopp );
    }

    public Optional<String> getPeriodisering()
    {
        return Optional.ofNullable( periodisering );
    }

    public Optional<String> getBerakningsgrund()
    {
        return Optional.ofNullable( berakningsgrund );
    }

    public Optional<String> getAndringsorsak()
    {
        return Optional.ofNullable( andringsorsak );
    }

    public Optional<String> getAvslagsanledning()
    {
        return Optional.ofNullable( avslagsanledning );
    }

}
