package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.dto.Dataleverans;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.Tjansteanteckning;
import se.fk.mimer.datamodel.v1.regel.Regel;

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
}
