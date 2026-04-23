package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.forman.Forman;
import se.fk.mimer.datamodel.v1.referensdata.forman.Formanstyp;

import java.util.UUID;

public class FormanFixtures
{
    private FormanFixtures() {}

    public static se.fk.mimer.datamodel.v1.forman.Formanstyp createFormanstyp()
    {
        return se.fk.mimer.datamodel.v1.forman.Formanstyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .formanstyp( Formanstyp.GEMENSAM )
                .build();
    }

    public static Forman createForman()
    {
        return Forman.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .formanstyp( createFormanstyp() )
                .produktnamn( "Testförmån" )
                .beskrivning( "Testbeskrivning" )
                .tillhorOrganisationsenhet( OrganisationsFixtures.createOrganisationsenhet() )
                .build();
    }

}
