package se.fk.mimer.datamodel.v1.handlaggning;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.anvandare.Anvandare;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Handlaggning
{
    @NotNull( message = "Handlaggning must have a proper ID." )
    private UUID id;
    @NotNull
    @Min( 1 )
    private int version;
    @NotNull
    private Handlaggningsspecifikation avserHandlaggningsspecifikation;
    private Anvandare avserBesvarAvAnvandare;
    @NotNull
    private HandlaggningsIDTyp handlaggningsIdTyp;
    @NotNull
    @NotBlank
    private String handlaggningsIdVarde;
    @NotNull
    private ZonedDateTime skapadTS;
    private ZonedDateTime avslutadTS;

    public Optional<Anvandare> getAvserBesvarAvAnvandare()
    {
        return Optional.ofNullable( avserBesvarAvAnvandare );
    }

    public Optional<String> getHandlaggningsIdVarde()
    {
        return Optional.ofNullable( handlaggningsIdVarde );
    }

    public Optional<ZonedDateTime> getSkapad()
    {
        return Optional.ofNullable( skapadTS );
    }

    public Optional<ZonedDateTime> getAvslutad()
    {
        return Optional.ofNullable( avslutadTS );
    }
}
