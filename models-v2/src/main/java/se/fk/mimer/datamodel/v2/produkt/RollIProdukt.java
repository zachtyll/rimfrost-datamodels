package se.fk.mimer.datamodel.v2.produkt;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
@Builder
public class RollIProdukt
{
    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull
    private String kundid;

    @NotNull
    private UUID produktid;

    @NotNull
    private Produktroller roll;
}
