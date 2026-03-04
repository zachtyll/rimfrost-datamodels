package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.utils.FixtureUtil;
import se.fk.mimer.datamodel.v1.produkt.EErbjudande;
import se.fk.mimer.datamodel.v1.produkt.EProduktnamn;
import se.fk.mimer.datamodel.v1.produkt.Erbjudande;
import se.fk.mimer.datamodel.v1.produkt.Produkt;
import se.fk.mimer.datamodel.v1.produkt.Produktroller;
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
    public static Erbjudande erbjudande() {
        return erbjudande(FixtureUtil.newId(), 0, "testErbjudande",
                EProduktnamn.UNDERHALLSSTOD,
                EErbjudande.UNDERHALLSSTOD);
    }

    public static Erbjudande erbjudande(UUID id,
                                        int revision,
                                        String namn,
                                        EProduktnamn produktnamn,
                                        EErbjudande eerbjudande) {
        return Erbjudande.builder()
                .id( id )
                .revision( revision )
                .erbjudandeNamn( namn )
                .produktnamn( produktnamn )
                .erbjudande( eerbjudande )
                .build();
    }

    public static Map<UUID, Erbjudande> erbjudandeMap() {
        Erbjudande e = erbjudande();
        Map<UUID, Erbjudande> map = new HashMap<>();
        map.put(e.getId(), e);
        return map;
    }

    // Produkt
    public static Produkt produkt() {
        return produkt(
                FixtureUtil.newId(),
                FixtureUtil.revision1(),
                EProduktnamn.UNDERHALLSSTOD,
                rollIProduktArray(),
                erbjudandeMap()
        );
    }

    public static Produkt produkt(UUID id,
                                  int revision,
                                  EProduktnamn namn,
                                  RollIProdukt[] roller,
                                  Map<UUID, Erbjudande> erbjudande) {
        RollIProdukt[] safeRoller =
                roller == null ? new RollIProdukt[0] : roller.clone();

        Map<UUID, Erbjudande> safeMap =
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
