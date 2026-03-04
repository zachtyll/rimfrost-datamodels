package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.jackson.deserializers.ZonedDateTimeDeserializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZonedDateTimeDeserializerTest {

    ZonedDateTimeDeserializer dut = new ZonedDateTimeDeserializer();
    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = CodecObjectMapperFactory.withEmptyVariants().create();
    }

    @Test
    void deserializeZonedDateTime() throws IOException {
        //Seems like timezones changes with summer/winter time
        String json = """
                { "hello": "2024-01-01T00:00:00.000000000+01:00[Europe/Stockholm]" }
                """;
        try( JsonParser parser = prepareParser( mapper, json ) )
        {
            ZonedDateTime dateTime = dut.deserialize(parser, mapper.getDeserializationContext());
            assertEquals(ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Europe/Stockholm")), dateTime);
        }
    }

    @Test
    void deserializeLocalDateTimeWithoutZone() throws IOException {
        String json = """
                { "hello": "2024-01-01T00:00:00" }
                """;
        try( JsonParser parser = prepareParser( mapper, json ) )
        {
            ZonedDateTime dateTime = dut.deserialize(parser, mapper.getDeserializationContext());
            assertEquals(ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Europe/Stockholm")), dateTime);
        }
    }

    @Test
    void deserializeLocalDate() throws IOException
    {
        String json = """
                { "hello": "2024-01-01" }
                """;
        try( JsonParser parser = prepareParser( mapper, json ) )
        {
            ZonedDateTime dateTime = dut.deserialize(parser, mapper.getDeserializationContext());
            assertEquals(ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Europe/Stockholm")), dateTime);
        }
    }

    @Test
    void invalidInput() throws IOException
    {
        String json = """
                { "hello": "Igår" }
                """;
        try( JsonParser parser = prepareParser( mapper, json ) )
        {
            IOException thrown = assertThrows(
                    IOException.class,
                    () -> dut.deserialize( parser, null ),
                    "Expected deserialize() to throw, but it didn't"
            );
            assertTrue(
                    thrown.getMessage() != null && thrown.getMessage().contains("Igår"),
                    "Unexpected message: " + thrown.getMessage()
            );
        }
    }

    private JsonParser prepareParser( ObjectMapper mapper, String json ) throws IOException
    {
        JsonParser parser = mapper.getFactory().createParser( json );
        while( parser.nextToken() != JsonToken.VALUE_STRING ) ;
        return parser;
    }
}