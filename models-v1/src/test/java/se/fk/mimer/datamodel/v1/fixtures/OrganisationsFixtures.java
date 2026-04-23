package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.organisation.JuridiskFormkod;
import se.fk.mimer.datamodel.v1.organisation.Organisation;
import se.fk.mimer.datamodel.v1.organisation.OrganisationsForm;
import se.fk.mimer.datamodel.v1.organisation.OrganisationsIdentitet;
import se.fk.mimer.datamodel.v1.organisation.Organisationsenhet;
import se.fk.mimer.datamodel.v1.referensdata.organisation.JuridiskFormkodKoder;
import se.fk.mimer.datamodel.v1.referensdata.organisation.OrganisationsFormkoder;
import se.fk.mimer.datamodel.v1.referensdata.organisation.OrganisationsIdentitetsKoder;

import java.util.UUID;

public class OrganisationsFixtures
{
    public OrganisationsFixtures()
    {
    }

    public static OrganisationsForm createOrganisationsForm()
    {
        return OrganisationsForm.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .kod( OrganisationsFormkoder.A_KASSA )
                .build();
    }

    public static JuridiskFormkod createJuridiskFormkod()
    {
        return JuridiskFormkod.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .kod( JuridiskFormkodKoder.AKTIEBOLAG_OVRIGA_49 )
                .build();
    }

    public static OrganisationsIdentitet createOrganisationsIdentitet()
    {
        return OrganisationsIdentitet.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .kod( OrganisationsIdentitetsKoder.ORGANISATIONSNUMMER )
                .build();
    }

    public static Organisation createOrganisation()
    {
        return Organisation.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .organisationsFormkod( createOrganisationsForm() )
                .juridiskFormkod( createJuridiskFormkod() )
                .organisationsIdentitetskod( createOrganisationsIdentitet() )
                .build();
    }

    public static Organisationsenhet createOrganisationsenhet()
    {
        return Organisationsenhet.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .namn( "Testenhet" )
                .kod( "TE" )
                .tillhorOrganisation( createOrganisation() )
                .build();
    }


}
