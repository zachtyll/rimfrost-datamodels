package se.fk.mimer.codec.v1.api;

import se.fk.mimer.codec.v1.dto.Dataleverans;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.exceptions.EncodeException;

/**
 * Publikt API för kodning och avkodning av dataleveranser enligt codec v1-formatet.
 *
 * <p>
 * Codecen ansvarar för att serialisera kanoniska modeller till en JSON-LD-baserad payload
 * (UTF-8 JSON bytes) som kapslas in i ({@link Dataleverans}), samt extraherar och validerar
 * sådan payload vid decode.
 *
 * <p>
 * Kontrakt:
 * <ul>
 *     <li>Payload representeras som JSON-LD-envelope innehållande {@code data} och {@code rawData}.</li>
 *     <li>Payload serialiseras till UTF-8 JSON och transporteras som base64Url.</li>
 *     <li>Decode metoder gör strikt typkontroll baserat på {@code data.@type}.</li>
 * </ul>
 *
 * <p>
 * Trådsäkerhet: Implementationer är avsedda att vara trådsäkra efter konstruktion.
 */
public interface Codec
{
    /**
     * Kodar kanonisk modell och rådata till en transportwrapper.
     *
     * @param request encode-request med kanonisk data, rawData och metadata.
     * @return transportwrapper med encoded payload
     * @throws EncodeException vid kodningsfel eller kontraktfel
     */
    Dataleverans encode( EncodeRequest request );

    /**
     * Avkodar en transportwrapper som förväntas innehålla ett Yrkande.
     *
     * @param leverans transportwrapper
     * @return avkodat resultat som innehåller byte-exakta payload bytes (source of truth)
     * @throws DecodeException vid strukturfel, okänd typ eller typ-mismatch
     */
    DecodedPayload decodeYrkande( Dataleverans leverans );

    /**
     * Avkodar en transportwrapper som förväntas innehålla en handläggning.
     *
     * @param leverans transportwrapper
     * @return avkodat resultat som innehåller byte-exakta payload bytes (source of truth)
     * @throws DecodeException vid strukturfel, okänd typ eller typ-mismatch
     */
    DecodedPayload decodeHandlaggning( Dataleverans leverans );
}
