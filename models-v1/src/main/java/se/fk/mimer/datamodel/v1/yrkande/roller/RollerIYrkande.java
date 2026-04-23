package se.fk.mimer.datamodel.v1.yrkande.roller;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.Yrkanderoller;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RollerIYrkande
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Yrkanderoller yrkanderoll;
}
