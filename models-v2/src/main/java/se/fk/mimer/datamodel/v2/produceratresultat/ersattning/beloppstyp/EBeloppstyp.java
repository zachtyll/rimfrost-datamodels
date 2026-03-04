package se.fk.mimer.datamodel.v2.produceratresultat.ersattning.beloppstyp;


import lombok.Getter;

public enum EBeloppstyp
{
    // AS
    SANKTIONSTAK(EBeloppstypKategori.AS),
    AS_GARANTIBELOPP(EBeloppstypKategori.AS),
    AKASSA(EBeloppstypKategori.AS),

    // FP
    LAGSTA(EBeloppstypKategori.FP),
    SGI_GRUNDAD(EBeloppstypKategori.FP),

    // UE
    UMFN(EBeloppstypKategori.UE),
    LAG_UE(EBeloppstypKategori.UE),
    HOG_UE(EBeloppstypKategori.UE);

    @Getter
    private final EBeloppstypKategori beloppstyp;

    EBeloppstyp( EBeloppstypKategori beloppstyp )
    {
        this.beloppstyp = beloppstyp;
    }
}
