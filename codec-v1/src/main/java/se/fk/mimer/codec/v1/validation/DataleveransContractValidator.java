package se.fk.mimer.codec.v1.validation;

import se.fk.mimer.codec.v1.dto.Dataleverans;
import se.fk.mimer.codec.v1.exceptions.DecodeException;

import java.util.Objects;

/**
 * Validerar att en {@link Dataleverans} har korrekt struktur och giltiga värden.
 *
 * <p> Kontrollerar att:
 * <ul>
 *     <li>{@code transportVersion} matchar förväntad version</li>
 *     <li>{@code payload} finns och är icke-blank</li>
 *     <li>{@code payloadEncoding} matchar förväntat värde</li>
 *     <li>{@code contentType} matchar förväntat värde</li>
 * </ul>
 *
 * <p>
 * Kastar {@link DecodeException} vid kontraktsbrott.
 */
public class DataleveransContractValidator
{
    private final String expectedTransportVersion;
    private final String expectedPayloadEncoding;
    private final String expectedContentType;

    public DataleveransContractValidator( String expectedTransportVersion, String expectedPayloadEncoding, String expectedContentType )
    {
        this.expectedTransportVersion = requireNonBlank( expectedTransportVersion, "expectedTransportVersion" );
        this.expectedPayloadEncoding = requireNonBlank( expectedPayloadEncoding, "expectedPayloadEncoding" );
        this.expectedContentType = requireNonBlank( expectedContentType, "expectedContentType" );
    }

    public void validate( Dataleverans dataleverans )
    {
        Objects.requireNonNull( dataleverans, "Dataleverans must not be null" );
        validateTransportVersion( dataleverans );

        if( isBlank( dataleverans.getPayload() ) )
        {
            throw new DecodeException( "Dataleverans.payload must not be blank" );
        }

        if( !( isBlank( dataleverans.getPayloadEncoding() ) ) && !dataleverans.getPayloadEncoding().equals( expectedPayloadEncoding ) )
        {
            throw new DecodeException( "Dataleverans.payloadEncoding must be '"
                    + expectedPayloadEncoding + "' but was '"
                    + dataleverans.getPayloadEncoding() + "'" );
        }

        if( !( isBlank( dataleverans.getContentType() ) ) && !dataleverans.getContentType().equals( expectedContentType ) )
        {
            throw new DecodeException( "Dataleverans.contentType must be '"
                    + expectedContentType + "' but was '"
                    + dataleverans.getContentType() + "'" );
        }
    }

    private static boolean isBlank( String value )
    {
        return value == null || value.isBlank();
    }

    private static String requireNonBlank( String value, String name )
    {
        if( value == null || value.isBlank() )
        {
            throw new IllegalArgumentException( name + " must not be blank" );
        }
        return value;
    }

    private static int parseMajor(String version) {
        String[] parts = version.split("\\.");
        if (parts.length != 2) {
            throw new DecodeException( "Invalid transportversion format: " + version);
        }

        try {
            return Integer.parseInt( parts[0] );
        } catch (NumberFormatException e) {
            throw new DecodeException( "Invalid transportVersion format: " + version );
        }
    }

    private void validateTransportVersion(Dataleverans dataleverans) {
        String actual = dataleverans.getTransportVersion();

        if (isBlank( actual )) {
            throw new DecodeException( "Missing transportVersion" );
        }

        int actualMajor = parseMajor(actual);
        int expectedMajor = parseMajor(expectedTransportVersion);

        if (actualMajor != expectedMajor) {
            throw new DecodeException(
                    "Unsupported transportVersion: " + actual
                    + ", supported major: " + expectedMajor
                    + ".x"
            );
        }
    }
}
