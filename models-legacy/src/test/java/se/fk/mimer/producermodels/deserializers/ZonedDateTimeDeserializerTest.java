package se.fk.mimer.producermodels.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fk.rdl.utils.errorhandling.exceptions.MimerException;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static se.fk.mimer.producermodels.utils.TestCDIUtility.inject;

public class ZonedDateTimeDeserializerTest {

    ZonedDateTimeDeserializer dut = new ZonedDateTimeDeserializer();
    ObjectMapper mapper = inject( ObjectMapper.class );

    @BeforeAll
    static void a()
    {
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
            MimerException thrown = assertThrows(
                    MimerException.class,
                    () -> dut.deserialize( parser, null ),
                    "Expected deserialize() to throw, but it didn't"
            );
            assertEquals("Could not parse Igår into ZonedDateTime, LocalDateTime, or LocalDate.", thrown.getMessage() );
        }
    }

    private JsonParser prepareParser( ObjectMapper mapper, String json ) throws IOException
    {
        JsonParser parser = mapper.getFactory().createParser( json );
        while( parser.nextToken() != JsonToken.VALUE_STRING ) ;
        return parser;
    }
}