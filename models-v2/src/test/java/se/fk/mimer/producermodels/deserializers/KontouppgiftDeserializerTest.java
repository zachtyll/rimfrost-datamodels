package se.fk.mimer.producermodels.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import se.fk.mimer.producermodels.v2.deserializers.KontouppgiftDeserializer;
import se.fk.mimer.producermodels.v2.model.Kontouppgift;
import com.fasterxml.jackson.core.JsonFactory;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KontouppgiftDeserializerTest {

    @Test
    public void deserializeKontouppgift() throws Exception {

        String json = "[{\"@type\":\"Kontouppgift\",\"id\":\"6c9625cd-19fa-4dcf-b38a-16dc8f02c29d\",\"revision\":1,\"kontotyp\":\"type1\",\"kontonummer\":\"abc-123\"},"
                + "{\"@type\":\"Kontouppgift\",\"id\":\"6a9625cd-19fa-4dcf-b38a-16dc8f02c29d\",\"revision\":1,\"kontotyp\":\"type2\",\"kontonummer\":\"def-456\"}]";

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();

        try (JsonParser parser = factory.createParser(new StringReader(json))) {
            DeserializationContext context = mapper.getDeserializationContext();

            KontouppgiftDeserializer deserializer = new KontouppgiftDeserializer();

            parser.nextToken();

            List<Kontouppgift> result = deserializer.deserialize(parser, context);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("type1", result.get(0).getKontotyp());
            assertEquals("abc-123", result.get(0).getKontonummer());
        }
    }
}
