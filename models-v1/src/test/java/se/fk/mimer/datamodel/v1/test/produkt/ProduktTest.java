package se.fk.mimer.datamodel.v1.test.produkt;

import com.fasterxml.uuid.Generators;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.fk.mimer.datamodel.v1.fixtures.ProduktFixtures;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Produktnamn;
import se.fk.mimer.datamodel.v1.produkt.Produkt;

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

        se.fk.mimer.datamodel.v1.produkt.Erbjudande res = produkt.addNewErbjudande( uuid, 0, "testerbjudande", Produktnamn.UNDERHALLSSTOD, Erbjudandetyp.BARNINKOMSTAVDRAG );
        assertInstanceOf( se.fk.mimer.datamodel.v1.produkt.Erbjudande.class, res );
        assertEquals( starterSize + 1, produkt.getErbjudanden().size() );
        se.fk.mimer.datamodel.v1.produkt.Erbjudande delres = produkt.removeErbjudande( uuid );
        assertInstanceOf( se.fk.mimer.datamodel.v1.produkt.Erbjudande.class, delres);
        assertEquals( starterSize, produkt.getErbjudanden().size() );
    }
}
