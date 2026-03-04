package se.fk.mimer.codec.v2.payload;

import java.util.Base64;

public final class Base64UrlCodec implements PayloadCodec
{

    @Override
    public String encode( byte[] payloadBytes )
    {
        if( payloadBytes == null )
        {
            throw new IllegalArgumentException( "bytes must not be null" );
        }
        return Base64.getUrlEncoder().withoutPadding().encodeToString( payloadBytes );
    }

    @Override
    public byte[] decode( String payload )
    {
        if( payload == null || payload.isBlank() )
        {
            throw new IllegalArgumentException( "payload must not be blank" );
        }
        return Base64.getUrlDecoder().decode( payload );
    }
}
