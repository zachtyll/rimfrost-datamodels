package se.fk.mimer.producermodels;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.validation.ValidFysiskPerson;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode( callSuper = true )
@Setter
@ValidFysiskPerson
public class FysiskPerson extends Person
{
    @Nullable
    private String personnummer;

    @Builder
    public FysiskPerson( UUID id, String kundid, int version, @Nullable String personnummer )
    {
        super( id, kundid, version );
        this.personnummer = personnummer;
    }

    public Optional<String> getPersonnummer()
    {
        return Optional.ofNullable( personnummer );
    }
}
