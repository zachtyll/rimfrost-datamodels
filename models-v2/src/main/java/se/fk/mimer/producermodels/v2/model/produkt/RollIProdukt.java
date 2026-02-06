package se.fk.mimer.producermodels.v2.model.produkt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.model.DataObject;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
@Builder
public class RollIProdukt implements DataObject
{
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull
    private String kundid;

    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull
    private UUID produktid;

    @NotNull
    private Produktroller roll;
}
