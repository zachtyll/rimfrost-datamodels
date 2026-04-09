package se.fk.mimer.datamodel.v1.produkt.forman;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.produkt.erbjudande.Formanstyp;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Forman
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Formanstyp formanstyp;
    private String beskrivning;

    public Optional<String> getBeskrivning()
    {
        return Optional.ofNullable( beskrivning );
    }
}
