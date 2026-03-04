package se.fk.mimer.producermodels.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import se.fk.mimer.producermodels.exceptions.MimerException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@Slf4j
public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime>
{
    //TODO: DONT LIKE HAVING 2 DESERIALISERS FOR THE SAME THING (SEE TIMEDESERIALIZER IN UTILS)
    // PROBLEM HERE IS THAT I DO NOT HAVE ACCESS TO THE DESERIALIZER AND MIMER EXCEPTIONS IN DATAMODELS.


    @Override
    public ZonedDateTime deserialize( JsonParser jp, DeserializationContext ctxt ) throws IOException
    {

        ZonedDateTime zonedDateTime;
        String dateString = jp.getValueAsString();

        try
        {
            zonedDateTime = ZonedDateTime.parse( dateString );
        }
        catch( DateTimeParseException e )
        {

            log.debug( "Could not parse {} into ZonedDateTime, trying LocalDateTime...", dateString );
            try
            {
                //If no timezone exists, assume "Europe/Stockholm"
                LocalDateTime localDateTime = LocalDateTime.parse( dateString );
                zonedDateTime = localDateTime.atZone( ZoneId.of( "Europe/Stockholm" ) );
            }
            catch( DateTimeParseException e2 )
            {
                log.debug( "Could not parse {} into LocalDateTime, trying LocalDate...", dateString );
                try
                {
                    //If only date exists, assume start of day in Europe/Stockholm +2:00" timezone
                    LocalDate localDate = LocalDate.parse( dateString );
                    zonedDateTime = localDate.atStartOfDay( ZoneId.of( "Europe/Stockholm" ) );
                }
                catch( DateTimeParseException e3 )
                {
                    throw new MimerException( String.format( "Could not parse %s into ZonedDateTime, LocalDateTime, " +
                                    "or LocalDate.",
                            dateString ), e3 );
                }
            }
        }
        return zonedDateTime;
    }
}