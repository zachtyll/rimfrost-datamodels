package se.fk.mimer.datamodel.v1.inkomst.anstallning;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AvtaladLoneforman
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private AvtaladLoneformanstyp typAvLoneforman;
    @NotNull
    private Anstallning gallerForAnstallning;
}
