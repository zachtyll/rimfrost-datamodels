package se.fk.mimer.datamodel.v1.regel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class ParagrafArtikel extends Lagrum
{
    public ParagrafArtikel( String individId, String paragrafArtikelId, String paragrafArtikelnamn,
                            String paragrafBeskrivning, String avdelningNamn, String underavdelningNamn,
                            String rubrik, String underrubrik, String referensTillAnnanForfattning,
                            ZonedDateTime paragrafGiltigFrom, ZonedDateTime paragrafGiltigTom )
    {
        super( individId );

    }
    @NotNull
    @NotBlank
    private String paragrafArtikelId;
    @NotNull
    @NotBlank
    private String paragrafArtikelNamn;
    @NotNull
    @NotBlank
    private String paragraBeskrivning;
    @NotNull
    @NotBlank
    private String avdelningNamn;
    private String underavdelningNamn;
    @NotNull
    @NotBlank
    private String rubrik;
    private String underrubrik;
    @NotNull
    @NotBlank
    private String referensTillAnnanForfattning;
    @NotNull
    private ZonedDateTime paragrafGiltigFrom;
    private ZonedDateTime paragrafGiltigTom;
}
