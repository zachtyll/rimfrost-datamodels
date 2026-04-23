package se.fk.mimer.datamodel.v1.inkomst.anstallning;

import jakarta.validation.constraints.NotNull;
import se.fk.mimer.datamodel.v1.referensdata.inkomst.Loneformaner;

import java.util.UUID;

public class AvtaladLoneformanstyp
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private Loneformaner loneforman;
}
