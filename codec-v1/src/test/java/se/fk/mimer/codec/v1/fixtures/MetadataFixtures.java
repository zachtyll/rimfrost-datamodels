package se.fk.mimer.codec.v1.fixtures;

import se.fk.mimer.codec.v1.dto.Metadata;
import se.fk.mimer.codec.v1.util.TestValues;

import java.time.Instant;

public class MetadataFixtures
{
    private MetadataFixtures() {}

    // SJP
    public static Metadata metadataSJP() {
        return Metadata.builder()
                .producentId( "SJP" )
                .publicerareId( "sjukpenning-1234" )
                .eventId( TestValues.uuidString() )
                .createdAt( Instant.now() )
                .breadcrumbId( "breadcrumb-" + TestValues.uuidString())
                .korrelationsId( "korr-" + TestValues.uuidString())
                .build();
    }
}
