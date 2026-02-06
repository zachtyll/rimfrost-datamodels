package se.fk.mimer.producermodels.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class TestdataUtil
{
    private static final String FILE_NOT_FOUND_MESSAGE_TEMPLATE = "No file named %s in /src/test/resources/";
    private static final String ERROR_MESSAGE_TEMPLATE = "Failed to read testfile: %s.";

    public static String readResourceFile( String filename )
    {
        try( var is = readResourceToStream( filename ) )
        {
            Objects.requireNonNull( is );
            final String s = new String( is.readAllBytes() );
            return s;
        }
        catch( IOException e )
        {
            throw new IllegalStateException( FILE_NOT_FOUND_MESSAGE_TEMPLATE.formatted( filename ), e );
        }
    }

    // IMPORTANT, this method returns an open InputStream. Therefore, it should always be called from a
    // try-with-resource clause.
    private static InputStream readResourceToStream( String filename )
    {
        var is = Thread.currentThread().getContextClassLoader().getResourceAsStream( filename );
        Objects.requireNonNull( is, FILE_NOT_FOUND_MESSAGE_TEMPLATE.formatted( filename ) );
        return is;
    }
}
