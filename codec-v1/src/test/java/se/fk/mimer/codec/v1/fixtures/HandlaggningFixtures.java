package se.fk.mimer.codec.v1.fixtures;

import se.fk.mimer.codec.v1.util.FixtureUtil;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;
import se.fk.mimer.datamodel.v1.handlaggning.HandlaggningsIDTyp;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggningsspecifikation;
import se.fk.mimer.datamodel.v1.referensdata.handlaggning.HandlaggningsIDTyper;

import java.time.ZonedDateTime;
import java.util.UUID;

public class HandlaggningFixtures
{
    private HandlaggningFixtures() {}

    public static Handlaggning valid() {
        return valid( FixtureUtil.newId(), FixtureUtil.revision1() );
    }

    public static Handlaggningsspecifikation createHandlaggningsspecifikation()
    {
        return Handlaggningsspecifikation.builder()
                .id( "123321" )
                .bpmnUrl( "http://example.com" )
                .namn( "testSpec" )
                .beskrivning( "" )
                .build();
    }

    public static HandlaggningsIDTyp createHandlaggningsIDTyp()
    {
        return HandlaggningsIDTyp.builder()
                .id( UUID.randomUUID() )
                .version( 1 )
                .beloppstyp( HandlaggningsIDTyper.AST_ARENDE )
                .build();
    }

    public static Handlaggning valid( UUID id, int revision) {
        return Handlaggning.builder()
                .id( id )
                .version( revision )
                .avserHandlaggningsspecifikation( createHandlaggningsspecifikation() )
                .handlaggningsIdTyp( createHandlaggningsIDTyp() )
                .handlaggningsIdVarde( UUID.randomUUID().toString() )
                .skapadTS( ZonedDateTime.now() )
                .build();
    }
}
