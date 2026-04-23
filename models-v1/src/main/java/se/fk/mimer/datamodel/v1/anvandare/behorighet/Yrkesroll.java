package se.fk.mimer.datamodel.v1.anvandare.behorighet;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.anvandare.Yrkesroller;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class Yrkesroll
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Yrkesroller yrkesroll;
}
