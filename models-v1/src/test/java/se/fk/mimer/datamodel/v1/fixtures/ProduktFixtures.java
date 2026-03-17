package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.utils.FixtureUtil;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Produktnamn;
import se.fk.mimer.datamodel.v1.produkt.Produkt;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Produktroller;
import se.fk.mimer.datamodel.v1.produkt.RollIProdukt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProduktFixtures
{
    private ProduktFixtures() {}

    public static String defaultPersonId() {
        return "1234567811234";
    }

    // RollIProdukt
    public static RollIProdukt rollIProdukt() {
        return RollIProdukt.builder()
                .id( FixtureUtil.newId() )
                .revision( FixtureUtil.revision1() )
                .kundid( defaultPersonId() )
                .produktid( FixtureUtil.newId() )
                .roll( Produktroller.PRODUKTAGARE )
                .build();
    }

    public static RollIProdukt[] rollIProduktArray() {
        return new RollIProdukt[] {rollIProdukt()};
    }

    // Erbjudande
    public static se.fk.mimer.datamodel.v1.produkt.Erbjudande erbjudande() {
        return erbjudande(FixtureUtil.newId(), 0, "testErbjudande",
                Produktnamn.UNDERHALLSSTOD,
                Erbjudandetyp.UNDERHALLSSTOD);
    }

    public static se.fk.mimer.datamodel.v1.produkt.Erbjudande erbjudande( UUID id,
                                                                          int revision,
                                                                          String namn,
                                                                          Produktnamn produktnamn,
                                                                          Erbjudandetyp erbjudandetyp) {
        return se.fk.mimer.datamodel.v1.produkt.Erbjudande.builder()
                .id( id )
                .revision( revision )
                .erbjudandeNamn( namn )
                .produktnamn( produktnamn )
                .erbjudandetyp( erbjudandetyp )
                .build();
    }

    public static Map<UUID, se.fk.mimer.datamodel.v1.produkt.Erbjudande> erbjudandeMap() {
        se.fk.mimer.datamodel.v1.produkt.Erbjudande e = erbjudande();
        Map<UUID, se.fk.mimer.datamodel.v1.produkt.Erbjudande> map = new HashMap<>();
        map.put(e.getId(), e);
        return map;
    }

    // Produkt
    public static Produkt produkt() {
        return produkt(
                FixtureUtil.newId(),
                FixtureUtil.revision1(),
                Produktnamn.UNDERHALLSSTOD,
                rollIProduktArray(),
                erbjudandeMap()
        );
    }

    public static Produkt produkt(UUID id,
                                  int revision,
                                  Produktnamn namn,
                                  RollIProdukt[] roller,
                                  Map<UUID, se.fk.mimer.datamodel.v1.produkt.Erbjudande> erbjudande) {
        RollIProdukt[] safeRoller =
                roller == null ? new RollIProdukt[0] : roller.clone();

        Map<UUID, se.fk.mimer.datamodel.v1.produkt.Erbjudande> safeMap =
                erbjudande == null ? new HashMap<>() : new HashMap<>(erbjudande);

        return Produkt.builder()
                .id( id )
                .revision( revision )
                .produktnamn( namn )
                .roller( safeRoller )
                .erbjudande( safeMap )
                .build();
    }


}
