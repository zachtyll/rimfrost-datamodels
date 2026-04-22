package se.fk.mimer.codec.v1.api;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import se.fk.mimer.codec.v1.dto.Metadata;

/**
 * Indata till {@link Codec#encode(EncodeRequest)}.
 *
 * <p>
 * {@code data} är den kanoniska modellen (t.ex. Yrkande/Handläggning).
 * {@code rawData} är rått underlag som kapslas in men inte JSON-LD taggas.
 *
 */
@Value
@Builder
public class EncodeRequest
{
    @NotNull
    Object data;

    @NotNull
    Object rawData;

    @NotNull
    Metadata metadata;
}
