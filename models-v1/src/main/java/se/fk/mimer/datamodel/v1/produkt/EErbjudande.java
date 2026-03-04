package se.fk.mimer.datamodel.v1.produkt;

import lombok.Getter;

public enum EErbjudande
{
    //Arbetsmarknadspolitiska Insatser
    GRUNDERSATTNING_VID_DELTAGANDE_I_PROGRAM( EProduktnamn.ARBETSMARKNADSPOLITISKA_INSATSER ),
    BOSTADSERSATTNING( EProduktnamn.ARBETSMARKNADSPOLITISKA_INSATSER ),
    ETABLERINGSTILLAGG( EProduktnamn.ARBETSMARKNADSPOLITISKA_INSATSER ),

    //Familjebidrag
    BOSTADS_OCH_NARINGSBIDRAG( EProduktnamn.FAMILJEBIDRAG ),
    FAMILJEPENNING( EProduktnamn.FAMILJEBIDRAG ),
    BEGRAVNINGSBIDRAG( EProduktnamn.FAMILJEBIDRAG ),

    //Underhållsstöd
    UNDERHALLSSTOD( EProduktnamn.UNDERHALLSSTOD ),
    FORLANGT_UNDERHALLSSTOD( EProduktnamn.UNDERHALLSSTOD ),
    UTFYLLNADSBIDRAG( EProduktnamn.UNDERHALLSSTOD ),
    BETALNINGSSKYLDIGHET( EProduktnamn.UNDERHALLSSTOD ),
    UNDERGALLSBIDRAG( EProduktnamn.UNDERHALLSSTOD ),
    UMGANGESAVDRAG( EProduktnamn.UNDERHALLSSTOD ),
    DIREKTAVDRAG( EProduktnamn.UNDERHALLSSTOD ),
    BARNINKOMSTAVDRAG( EProduktnamn.UNDERHALLSSTOD ),
    ANSTAND( EProduktnamn.UNDERHALLSSTOD );

    @Getter
    private final EProduktnamn produktnamn;

    EErbjudande( EProduktnamn produktnamn )
    {
        this.produktnamn = produktnamn;
    }
}
