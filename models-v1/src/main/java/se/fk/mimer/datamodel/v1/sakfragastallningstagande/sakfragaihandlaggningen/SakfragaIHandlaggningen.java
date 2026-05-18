package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import se.fk.mimer.datamodel.v1.forman.sakfraga.Sakfraga;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.SakfragaStallningstagande;

@Getter
@Setter
@SuperBuilder
public abstract class SakfragaIHandlaggningen extends SakfragaStallningstagande
{
    @NotNull
    private Sakfraga avserSakfraga;
}
