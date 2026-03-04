package se.fk.mimer.codec.v2.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Shared tiny helpers for fixtures.
 */
public final class TestValues
{
    private static final ZoneId ZONE = ZoneId.of("Europe/Stockholm");
    private static final AtomicInteger REVISION_COUNTER = new AtomicInteger(1);

    private TestValues() {}

    public static UUID uuid() {
        return UUID.randomUUID();
    }

    public static String uuidString() {
        return uuid().toString();
    }

    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZONE);
    }

    public static ZonedDateTime fixed() {
        return ZonedDateTime.of(2026, 2, 17, 10, 0, 0, 0, ZONE);
    }

    public static int revision() {
        return REVISION_COUNTER.getAndIncrement();
    }

}
