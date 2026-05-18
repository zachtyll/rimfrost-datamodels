package se.fk.mimer.datamodel.v1.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.person.adress.Adress;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class FysiskPerson
{
    @NotNull
    private UUID id;
    @NotNull
    @NotBlank
    private String efternamn;
    @NotNull
    @NotBlank
    private String fornamn;
    @NotNull
    @NotBlank
    private String kon;
    private Adress adress;

    public Optional<Adress> getAdress()
    {
        return Optional.ofNullable( adress );
    }
}
