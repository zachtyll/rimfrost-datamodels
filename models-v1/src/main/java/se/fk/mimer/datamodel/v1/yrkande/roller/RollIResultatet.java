package se.fk.mimer.datamodel.v1.yrkande.roller;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.person.Persontyp;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.SakfragaStallningstagande;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RollIResultatet
{
    @NotNull
    private UUID id;
    @NotNull
    private IDTyp individ;
    @NotNull
    private Yrkanderoll yrkanderoll;
    @NotNull
    private SakfragaStallningstagande avserSakfragaStallningstagande;
    @NotNull
    private Persontyp avserPersontyp;
}
