package se.fk.mimer.codec.v2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

/**
 * Yttre transportkontrakt för leverans av en förmåns data.
 *
 * <p>
 * {@code payload} innehåller base64Url-kodade UTF-8 JSON bytes av en JSON-LD-envelope
 * med fälten {@code data} och {@code rawData}.
 *
 * <p>
 * {@code transportVersion} identierar vilken version.
 *
 * <p>
 * {@code contentType} och {@code payloadEncoding} beskriver payloadens format och kodning.
 */
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dataleverans
{
    @NotBlank
    String transportVersion;

    @Valid
    @NotNull
    Metadata metadata;

    @NotBlank
    String payload;

    String contentType;

    String payloadEncoding;
}
