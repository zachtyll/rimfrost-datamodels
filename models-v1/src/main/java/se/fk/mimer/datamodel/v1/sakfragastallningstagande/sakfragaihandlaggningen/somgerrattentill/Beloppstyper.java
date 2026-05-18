package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfragaihandlaggningen.somgerrattentill;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Beloppstyper
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private se.fk.mimer.datamodel.v1.referensdata.sakfraga.stallningstagande.Beloppstyper beloppstyper;
}
