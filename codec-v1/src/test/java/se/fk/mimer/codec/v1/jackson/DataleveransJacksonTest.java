package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.dto.Dataleverans;
import se.fk.mimer.datamodel.v1.lagrum.Regel;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DataleveransJacksonTest
{
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule( new JavaTimeModule() );

    @Test
    void shouldDeserializeDataleverans() throws Exception {

        String json = """
                {
                 "transportVersion": "1.0",
                 "metadata": {
                 "producentId": "SJP1",
                 "publicerareId": "publicerare1",
                 "eventId": "event1",
                 "createdAt": "2024-01-01T00:00:00Z"
                 },
                 "payload": "eyJAdHlwZSI6InRlc3QifQ"
                }
                """;

        Dataleverans dataleverans = mapper.readValue( json, Dataleverans.class );

        assertEquals("1.0", dataleverans.getTransportVersion());
        assertEquals( "SJP1", dataleverans.getMetadata().getProducentId() );
        assertEquals( "event1", dataleverans.getMetadata().getEventId() );
        assertEquals( Instant.parse("2024-01-01T00:00:00Z"), dataleverans.getMetadata().getCreatedAt());
        assertEquals( "eyJAdHlwZSI6InRlc3QifQ", dataleverans.getPayload() );
    }

    @Test
    void shouldDeserializeJacksonizedObject() throws Exception
    {
        String json = """
                {
                  "id": "550e8400-e29b-41d4-a716-446655440000",
                  "version": 1,
                  "objektId": "123123",
                  "lagrum": [
                    {
                      "id": "550e8400-e29b-41d4-a716-446655440087",
                      "revision": 1,
                      "forfattning": "Brottsbalken",
                      "kapitel": "1a",
                      "paragraf": "1a",
                      "stycke": "2a",
                      "punkt": "2a",
                      "giltighetstid":
                        {
                            "from": "2025-03-01T12:00:00+01:00",
                            "tom": "2026-03-01T12:00:00+01:00"
                        }
                    }
                  ]
                }
                """;

        Regel regel = mapper.readValue( json, Regel.class );
        assertInstanceOf( Regel.class, regel );
    }
}
