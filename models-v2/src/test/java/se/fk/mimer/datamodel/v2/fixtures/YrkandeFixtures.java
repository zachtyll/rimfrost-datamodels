package se.fk.mimer.datamodel.v2.fixtures;

import se.fk.mimer.datamodel.v2.utils.FixtureUtil;
import se.fk.mimer.datamodel.v2.yrkande.Yrkande;

import java.util.UUID;

public class YrkandeFixtures
{
    private YrkandeFixtures() {}

    public static Yrkande valid() {
        return valid( FixtureUtil.newId(), FixtureUtil.revision1() );
    }

    public static Yrkande valid(UUID id, int revision) {
        return Yrkande.builder()
                .id( id )
                .revision( revision )
                .build();
    }
}
