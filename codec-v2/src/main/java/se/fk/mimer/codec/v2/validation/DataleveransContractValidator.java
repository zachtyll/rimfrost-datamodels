package se.fk.mimer.codec.v2.validation;

import se.fk.mimer.codec.v2.dto.Dataleverans;
import se.fk.mimer.codec.v2.exceptions.DecodeException;

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

        if (!expectedTransportVersion.equals( dataleverans.getTransportVersion() )) {
            throw new DecodeException(
                    "Unsupported transportVersion: " + dataleverans.getTransportVersion()
                    + ", expected: " + expectedTransportVersion
            );
        }

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
}
