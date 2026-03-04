package se.fk.mimer.codec.v2.jackson.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime>
{
    private static final ZoneId DEFAULT_ZONE = ZoneId.of( "Europe/Stockholm" );

    @Override
    public ZonedDateTime deserialize( JsonParser jp, DeserializationContext ctxt ) throws IOException
    {
        String dateString = jp.getValueAsString();
        if( dateString == null || dateString.isBlank()) return null;

        // Full ZonedDateTime
        try {
            return ZonedDateTime.parse(dateString);
        } catch (DateTimeParseException ignored) {
            // fall through
        }

        // LocalDateTime without zone
        try {
            LocalDateTime localDateTime = LocalDateTime.parse( dateString );
            return localDateTime.atZone( DEFAULT_ZONE );
        } catch (DateTimeParseException ignored) {
            // fall through
        }

        // LocalDate
        try {
            LocalDate localDate = LocalDate.parse( dateString );
            return localDate.atStartOfDay(DEFAULT_ZONE);
        } catch( DateTimeParseException e ) {
            throw new IOException("Could not parse" + dateString + "ZonedDateTime, LocalDateTime or LocalDate: ", e);
        }
    }
}
