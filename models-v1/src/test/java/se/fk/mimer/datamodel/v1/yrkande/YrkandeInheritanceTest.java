package se.fk.mimer.datamodel.v1.yrkande;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YrkandeInheritanceTest {

    /**
     * Lokal subklass för testning.
     */
    @Jacksonized
    @Getter
    @Setter
    @SuperBuilder
    static class TestExtendedYrkande extends Yrkande {
        private String revision;
    }

    @Test
    void should_be_able_to_build_subClass_with_baseFields_and_ownFields() {
        UUID id = UUID.randomUUID();

        TestExtendedYrkande extendedYrkande = TestExtendedYrkande.builder()
                .id(id)
                .version(1)
                .revision("rev-1")
                .build();

        assertEquals(id, extendedYrkande.getId());
        assertEquals(1, extendedYrkande.getVersion());
        assertEquals("rev-1", extendedYrkande.getRevision());
    }
}
