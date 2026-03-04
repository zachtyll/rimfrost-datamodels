package se.fk.mimer.codec.v2.payload;

/**
 * Kodning och avkodning av payload mellan byte-representation och strängrepresentation.
 *
 * <p>
 * Implementationer ska vara reversibla: decode(encode(bytes)) ska returnera original bytes.
 */
public interface PayloadCodec
{
    /**
     * Kodar payload-bytes till en specifik strängrepresentation.
     *
     * @param payloadBytes payload-bytes
     * @return encoded payload-representation
     */
    String encode(byte[] payloadBytes);

    /**
     * Avkodar payload-sträng till original payload-bytes.
     *
     * @param payload encoded payload-sträng
     * @return original payload-bytes
     */
    byte[] decode(String payload);
}
