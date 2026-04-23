package se.fk.mimer.datamodel.v1.forman;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Formanstyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private se.fk.mimer.datamodel.v1.referensdata.forman.Formanstyp formanstyp;
}
