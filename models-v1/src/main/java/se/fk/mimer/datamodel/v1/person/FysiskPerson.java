package se.fk.mimer.datamodel.v1.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.person.adress.Adress;

import java.util.Optional;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class FysiskPerson
{
    @NotNull
    @NotBlank
    private String id;
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
