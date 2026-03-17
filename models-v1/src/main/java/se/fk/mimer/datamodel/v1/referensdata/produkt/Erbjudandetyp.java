package se.fk.mimer.datamodel.v1.referensdata.produkt;

import lombok.Getter;

public enum Erbjudandetyp
{
    //Arbetsmarknadspolitiska Insatser
    GRUNDERSATTNING_VID_DELTAGANDE_I_PROGRAM( Produktnamn.ARBETSMARKNADSPOLITISKA_INSATSER ),
    BOSTADSERSATTNING( Produktnamn.ARBETSMARKNADSPOLITISKA_INSATSER ),
    ETABLERINGSTILLAGG( Produktnamn.ARBETSMARKNADSPOLITISKA_INSATSER ),

    //Familjebidrag
    BOSTADS_OCH_NARINGSBIDRAG( Produktnamn.FAMILJEBIDRAG ),
    FAMILJEPENNING( Produktnamn.FAMILJEBIDRAG ),
    BEGRAVNINGSBIDRAG( Produktnamn.FAMILJEBIDRAG ),

    //Underhållsstöd
    UNDERHALLSSTOD( Produktnamn.UNDERHALLSSTOD ),
    FORLANGT_UNDERHALLSSTOD( Produktnamn.UNDERHALLSSTOD ),
    UTFYLLNADSBIDRAG( Produktnamn.UNDERHALLSSTOD ),
    BETALNINGSSKYLDIGHET( Produktnamn.UNDERHALLSSTOD ),
    UNDERGALLSBIDRAG( Produktnamn.UNDERHALLSSTOD ),
    UMGANGESAVDRAG( Produktnamn.UNDERHALLSSTOD ),
    DIREKTAVDRAG( Produktnamn.UNDERHALLSSTOD ),
    BARNINKOMSTAVDRAG( Produktnamn.UNDERHALLSSTOD ),
    ANSTAND( Produktnamn.UNDERHALLSSTOD );

    @Getter
    private final Produktnamn produktnamn;

    Erbjudandetyp( Produktnamn produktnamn )
    {
        this.produktnamn = produktnamn;
    }
}
