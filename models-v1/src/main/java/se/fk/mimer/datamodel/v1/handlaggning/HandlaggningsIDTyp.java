package se.fk.mimer.datamodel.v1.handlaggning;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.handlaggning.HandlaggningsIDTyper;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class HandlaggningsIDTyp
{
    @NotNull
    private UUID id;
    @NotNull
    @Min( 1 )
    private int version;
    @NotNull
    private HandlaggningsIDTyper beloppstyp;
}
