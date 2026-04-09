package se.fk.mimer.datamodel.v1.person;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.yrkande.RollIYrkande;

import java.util.Map;
import java.util.UUID;

@Accessors( chain = true )
@Setter
@Getter
@SuperBuilder
public abstract class Person
{
    public Person(UUID id, int revision, String kundid, Map<UUID, RollIYrkande> rollIKundbehov)
    {
        this.id = id;
        this.revision = revision;
        this.kundid = kundid;
        this.rollIKundbehov = rollIKundbehov;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull
    protected String kundid;

    @NotNull
    protected Map<UUID, RollIYrkande> rollIKundbehov;
}
