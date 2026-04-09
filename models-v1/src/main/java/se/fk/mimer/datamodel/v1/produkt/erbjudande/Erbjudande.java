package se.fk.mimer.datamodel.v1.produkt.erbjudande;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.produkt.forman.Forman;

import java.util.Collection;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Erbjudande
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Erbjudandetyp erbjudande;
    @NotNull
    private Collection<Forman> ingarIProdukt;
}
