package se.fk.mimer.datamodel.v2.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Shared tiny helpers for fixtures.
 */
public final class FixtureUtil
{
    private FixtureUtil() {}

    public static UUID newId() {
        return UUID.randomUUID();
    }

    public static int revision1() {
        return 1;
    }

    public static ZonedDateTime fixedDate() {
        return ZonedDateTime.of(
                2024,
                1,
                1,
                10,
                0,
                0,
                0,
                ZoneId.of("UTC")
        );
    }
}
