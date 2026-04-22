package se.fk.mimer.codec.v1.jsonld.extract;

import se.fk.mimer.codec.v1.exceptions.DecodeException;

/**
 * API för att extrahera delar av en payload-envelope.
 *
 * <p>
 * Returnerar JSON som UTF-8-bytes.
 *
 * <p>
 * Observera:
 * <ul>
 *     <li>Extraherade delmängder returneras som reserialiserade bytes och är inte garanterat byte-identiska
 *     med originalets subträd.</li>
 *     <li>Byte-exakt garanti gäller endast hela payload-bytes.</li>
 * </ul>
 */
public interface PayloadInspector
{
    /**
     * Extraherar {@code rawData} från payload-envelope.
     *
     * @param payloadBytes UTF-8 JSON payload (envelope)
     * @return rawData som UTF-8 JSON bytes
     * @throws DecodeException om payload saknar rawData eller har fel struktur.
     */
    byte[] extractRawDataJsonBytes(byte[] payloadBytes);

    /**
     * Extraherar {@code @graph} bas-data från payload-envelope.
     *
     * @param payloadBytes UTF-8 JSON payload-envelope.
     * @return data som UTF-8 JSON bytes
     * @throws DecodeException om payload saknar bas-data eller har fel struktur.
     */
    byte[] extractBaseDataJsonBytes(byte[] payloadBytes);
}
