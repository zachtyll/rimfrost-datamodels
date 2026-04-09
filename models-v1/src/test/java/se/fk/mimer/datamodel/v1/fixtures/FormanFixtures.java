package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.produkt.forman.Forman;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Formanstyp;
import java.util.UUID;

public class FormanFixtures
{
    private FormanFixtures() {}

    public static se.fk.mimer.datamodel.v1.produkt.erbjudande.Formanstyp getFormanstyp()
    {
        return se.fk.mimer.datamodel.v1.produkt.erbjudande.Formanstyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .formanstyp( Formanstyp.GEMENSAM )
                .build();
    }

    public static Forman getForman()
    {
        return Forman.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .formanstyp( getFormanstyp() )
                .build();
    }

}
