package se.fk.mimer.producermodels.v2.model.kundbehov;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.deserializers.ZonedDateTimeDeserializer;
import se.fk.mimer.producermodels.v2.model.DataObject;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.produkt.Erbjudande;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Builder
public class Kundbehov implements DataObject
{
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull
    @Getter
    private UUID id;

    @NotNull
    @Getter
    private int revision;

    private Kundbehovsstatus kundbehovsstatus;

    private Avsikt avsikt;

    private String andringsorsak;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    private ZonedDateTime kundbehovsdatum;

    private Period period;

    private Erbjudande avserErbjudande;

    @Getter
    private List<RollIKundbehov> roller;

    public Optional<Kundbehovsstatus> getKundbehovsstatus()
    {
        return Optional.ofNullable( kundbehovsstatus );
    }

    public Optional<Avsikt> getAvsikt()
    {
        return Optional.ofNullable( avsikt );
    }

    public Optional<ZonedDateTime> getKundbehovsdatum()
    {
        return Optional.ofNullable( kundbehovsdatum );
    }

    public Optional<Period> getPeriod()
    {
        return Optional.ofNullable( period );
    }

    public Optional<Erbjudande> getAvserErbjudande()
    {
        return Optional.ofNullable( avserErbjudande );
    }

    public Optional<String> getAndringsorsak()
    {
        return Optional.ofNullable( andringsorsak );
    }
}
