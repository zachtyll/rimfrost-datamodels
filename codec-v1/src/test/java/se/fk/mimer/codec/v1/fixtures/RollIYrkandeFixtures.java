package se.fk.mimer.codec.v1.fixtures;

import se.fk.mimer.codec.v1.util.FixtureUtil;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;

import java.util.UUID;

import static se.fk.mimer.codec.v1.fixtures.PersonFixtures.createIdTyp;
import static se.fk.mimer.codec.v1.fixtures.PersonFixtures.createPerson;
import static se.fk.mimer.codec.v1.fixtures.YrkandeFixtures.createRollerIYrkande;


public class RollIYrkandeFixtures
{
    private RollIYrkandeFixtures() {}

    public static RollIYrkande valid()
    {
        return valid( FixtureUtil.newId(), FixtureUtil.revision1() );
    }

    public static RollIYrkande valid( UUID id, int version )
    {
        return RollIYrkande.builder()
                .id( UUID.randomUUID() )
                .individ( createIdTyp() )
                .yrkanderoll( createRollerIYrkande() )
                .avserYrkande( UUID.randomUUID() )
                .avserPersontyp( createPerson() )
                .build();
    }
}
