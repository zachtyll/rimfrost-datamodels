package se.fk.mimer.datamodel.v1.yrkande.roller;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.YrkanderollTyper;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Yrkanderoll
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private YrkanderollTyper yrkanderollTyp;
}
