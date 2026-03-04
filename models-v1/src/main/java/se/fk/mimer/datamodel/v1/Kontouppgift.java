package se.fk.mimer.datamodel.v1;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
@Builder
public class Kontouppgift
{
    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @Nullable
    private String kontotyp;

    @Nullable
    private String kontonummer;

    @NotNull( message = "Kontouppgift must have the tillatUtbetalning value set.")
    private Boolean tillatUtbetalning;
}
