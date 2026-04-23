package se.fk.mimer.datamodel.v1.fixtures;

import se.fk.mimer.datamodel.v1.utils.FixtureUtil;
import se.fk.mimer.datamodel.v1.yrkande.roller.RollIYrkande;

import java.util.UUID;

import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createIdTyp;
import static se.fk.mimer.datamodel.v1.fixtures.PersonFixtures.createPerson;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createRollerIYrkande;
import static se.fk.mimer.datamodel.v1.fixtures.YrkandeFixtures.createYrkande;

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
                .individ( createIdTyp() )
                .roll( createRollerIYrkande() )
                .avserYrkande( createYrkande() )
                .avserPerson( createPerson() )
                .build();
    }
}
