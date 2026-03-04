package se.fk.mimer.datamodel.v2.fixtures;

import se.fk.mimer.datamodel.v2.utils.FixtureUtil;
import se.fk.mimer.datamodel.v2.handlaggning.Handlaggning;

import java.util.UUID;

public class HandlaggningFixtures
{
    private HandlaggningFixtures() {}

    public static Handlaggning valid() {
        return valid( FixtureUtil.newId(), FixtureUtil.revision1() );
    }

    public static Handlaggning valid( UUID id, int revision) {
        return Handlaggning.builder()
                .id( id )
                .revision( revision )
                .build();
    }
}
