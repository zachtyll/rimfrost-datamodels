package se.fk.mimer.codec.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

/**
 * Metadata för en leverans.
 */
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metadata
{
    @NotBlank
    String producentId;

    @NotBlank
    String publicerareId;

    @NotBlank
    String eventId;

    @NotNull
    Instant createdAt;

    // PARAM i http header
    String breadcrumbId;

    String korrelationsId;
}
