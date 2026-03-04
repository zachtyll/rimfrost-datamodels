package se.fk.mimer.codec.v2.api;

import lombok.Builder;
import lombok.Value;

/**
 * Resultat från decode.
 *
 * <p>
 * Innehåller de exakta UTF-8 JSON-LD-bytes som extraherats från transportens payload.
 * Dessa bytes ska betraktas som systemets source of truth och kan lagras och replay:as utan
 * informationsförlust.
 *
 * <p>
 * Notera: Codecen kan internt deserialisera payloaden (t.ex. för typkontroll och validering),
 * men exponerar inte kanoniska POJOs som del av det publika kontraktet.
 */
@Value
@Builder
public class DecodedPayload
{
    byte[] payloadBytes;
}