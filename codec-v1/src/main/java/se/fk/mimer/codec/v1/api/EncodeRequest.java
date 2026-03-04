package se.fk.mimer.codec.v1.api;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import se.fk.mimer.codec.v1.dto.Metadata;
import se.fk.mimer.codec.v1.jsonld.JsonLdTaggingMode;

/**
 * Indata till {@link Codec#encode(EncodeRequest)}.
 *
 * <p>
 * {@code data} är den kanoniska modellen (t.ex. Yrkande/Handläggning).
 * {@code rawData} är rått underlag som kapslas in men inte JSON-LD raggas.
 *
 * <p>
 * {@code taggingMode} styr var JSON-LD nycklar injiceras:
 * root och/eller data. RawData taggas aldrig.
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

    @Builder.Default
    JsonLdTaggingMode taggingMode = JsonLdTaggingMode.ROOT_AND_DATA;
}
