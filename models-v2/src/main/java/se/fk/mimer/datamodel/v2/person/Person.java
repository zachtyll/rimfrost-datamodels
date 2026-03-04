package se.fk.mimer.datamodel.v2.person;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.yrkande.RollIYrkande;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
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
