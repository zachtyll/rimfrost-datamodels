package se.fk.mimer.datamodel.v1.yrkande.roller;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.person.Persontyp;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class RollIYrkande
{
    @NotNull
    private UUID id;
    @NotNull
    private IDTyp individ;
    @NotNull
    private Yrkanderoll yrkanderoll;
    @NotNull
    private UUID avserYrkande;
    @NotNull
    private Persontyp avserPersontyp;
}
