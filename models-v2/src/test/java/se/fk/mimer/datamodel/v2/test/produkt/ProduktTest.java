package se.fk.mimer.datamodel.v2.test.produkt;

import com.fasterxml.uuid.Generators;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v2.fixtures.ProduktFixtures;
import se.fk.mimer.datamodel.v2.produkt.EErbjudande;
import se.fk.mimer.datamodel.v2.produkt.EProduktnamn;
import se.fk.mimer.datamodel.v2.produkt.Erbjudande;
import se.fk.mimer.datamodel.v2.produkt.Produkt;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class ProduktTest
{
    @Test
    @Tag( "ProduktCanAddAndRemoveErbjudandenTest" )
    void ProduktOwnsErbjudande()
    {
        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        Produkt produkt = ProduktFixtures.produkt();
        int starterSize = produkt.getErbjudanden().size();

        Erbjudande res = produkt.addNewErbjudande( uuid, 0, "testerbjudande", EProduktnamn.UNDERHALLSSTOD, EErbjudande.BARNINKOMSTAVDRAG );
        assertInstanceOf( Erbjudande.class, res );
        assertEquals( starterSize + 1, produkt.getErbjudanden().size() );
        Erbjudande delres = produkt.removeErbjudande( uuid );
        assertInstanceOf( Erbjudande.class, delres);
        assertEquals( starterSize, produkt.getErbjudanden().size() );
    }
}
