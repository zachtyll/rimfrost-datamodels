package se.fk.mimer.datamodel.v1.referensdata.produceratresultat;

import lombok.Getter;

public enum Inkomsttyp
{
    // BGI
    A( InkomsttypKategori.BGI),
    B( InkomsttypKategori.BGI),
    C( InkomsttypKategori.BGI),

    // SGI
    VANLIG( InkomsttypKategori.SGI),
    SKYDDAD( InkomsttypKategori.SGI),
    VILANDE( InkomsttypKategori.SGI);

    @Getter
    private final InkomsttypKategori kategori;

    Inkomsttyp( InkomsttypKategori kategori)
    {
        this.kategori = kategori;
    }
}
