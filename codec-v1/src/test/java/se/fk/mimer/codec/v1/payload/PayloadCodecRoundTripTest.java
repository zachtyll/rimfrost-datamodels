package se.fk.mimer.codec.v1.payload;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PayloadCodecRoundTripTest
{
    private final PayloadCodec codec = new Base64UrlCodec();

    @Test
    void roundtrip_preservers_bytes_exactly() {
        byte[] bytes = "{\"hello\":\"world\",\"n\":123}".getBytes( StandardCharsets.UTF_8);

        String encoded = codec.encode( bytes );
        byte[] decoded = codec.decode( encoded );

        assertArrayEquals(bytes, decoded);
    }

    @Test
    void encoded_is_url_safe_and_no_padding() {
        byte[] bytes = new byte[] { (byte)0xfb, (byte)0xff, (byte)0x00, (byte)0x10 };
        String encoded = codec.encode( bytes );

        assertFalse( encoded.contains( "+" ) );
        assertFalse( encoded.contains( "/" ) );
        assertFalse( encoded.contains( "=" ) );

        assertArrayEquals( bytes, codec.decode( encoded ) );
    }
}
