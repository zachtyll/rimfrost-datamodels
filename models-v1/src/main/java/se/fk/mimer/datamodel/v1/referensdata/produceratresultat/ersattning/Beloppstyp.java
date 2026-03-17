package se.fk.mimer.datamodel.v1.referensdata.produceratresultat.ersattning;


import lombok.Getter;

public enum Beloppstyp
{
    // AS
    SANKTIONSTAK( BeloppstypKategori.AS),
    AS_GARANTIBELOPP( BeloppstypKategori.AS),
    AKASSA( BeloppstypKategori.AS),

    // FP
    LAGSTA( BeloppstypKategori.FP),
    SGI_GRUNDAD( BeloppstypKategori.FP),

    // UE
    UMFN( BeloppstypKategori.UE),
    LAG_UE( BeloppstypKategori.UE),
    HOG_UE( BeloppstypKategori.UE);

    @Getter
    private final BeloppstypKategori beloppstyp;

    Beloppstyp( BeloppstypKategori beloppstyp )
    {
        this.beloppstyp = beloppstyp;
    }
}
