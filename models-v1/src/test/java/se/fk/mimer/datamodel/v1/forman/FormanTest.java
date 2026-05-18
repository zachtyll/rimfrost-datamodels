package se.fk.mimer.datamodel.v1.forman;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.FormanFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FormanTest
{
    @Test
    @Tag( "ProduktCanAddAndRemoveErbjudandenTest" )
    void FormanInstansiatesCorrectly()
    {
        Forman forman = FormanFixtures.createForman();

        assertInstanceOf( Forman.class, forman );
        assertInstanceOf( Formanstyp.class, forman.getFormanstyp() );
        assertNotNull( forman.getBeskrivning() );
        forman.setBeskrivning( "Testbeskrivning" );
        assertEquals( "Testbeskrivning", forman.getBeskrivning() );
    }
}
