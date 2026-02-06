package se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst;

import lombok.Getter;

public enum EInkomsttyp
{
    // BGI
    A(EInkomsttypKategori.BGI),
    B(EInkomsttypKategori.BGI),
    C(EInkomsttypKategori.BGI),

    // SGI
    VANLIG(EInkomsttypKategori.SGI),
    SKYDDAD(EInkomsttypKategori.SGI),
    VILANDE(EInkomsttypKategori.SGI);

    @Getter
    private final EInkomsttypKategori kategori;

    EInkomsttyp( EInkomsttypKategori kategori)
    {
        this.kategori = kategori;
    }
}
