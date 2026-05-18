package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.SakfragaIHandlaggningen;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class RattenTillPeriod extends SakfragaIHandlaggningen
{

    @NotNull
    private Double omfattningIProcent;
}
