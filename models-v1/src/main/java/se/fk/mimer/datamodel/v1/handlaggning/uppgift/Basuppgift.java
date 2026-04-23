package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggning;

import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Basuppgift
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private ZonedDateTime skapadTS;
    @NotNull
    private IDTyp utforare;
    @NotNull
    private Handlaggning utforsIHandlaggning;
}
