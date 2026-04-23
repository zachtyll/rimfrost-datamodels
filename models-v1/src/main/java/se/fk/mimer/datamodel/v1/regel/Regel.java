package se.fk.mimer.datamodel.v1.regel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@Jacksonized
@Getter
@Setter
@Builder
public class Regel
{
    private UUID id;
    private int version;
    private String objektId;
    private Collection<Lagrum> lagrum;
}
