package se.fk.mimer.producermodels.v2.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class KlassificeratObjekt implements DataObject
{
    @NotNull (message = "Every object must have a proper ID set.")
    @JsonDeserialize(using = UUIDDeserializer.class)
    private UUID id;

    @NotNull
    private int revision;

    @NotNull
    private String variant;
}
