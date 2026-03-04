package se.fk.mimer.datamodel.v2.lagrum;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v2.Period;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class Lagrum
{
    @Builder
    public Lagrum( UUID id, int revision, String forfattning, String kapitel, String paragraf,
                   @Nullable String stycke, @Nullable String punkt, Period giltighetstid)
    {
        this.id = id;
        this.revision = revision;
        this.forfattning = forfattning;
        this.kapitel = kapitel;
        this.paragraf = paragraf;
        this.stycke = stycke;
        this.punkt = punkt;
        this.giltighetstid = giltighetstid;
    }

    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull( message = "A Lagrum has to point at a Forfattning.")
    private String forfattning;

    @NotNull( message = "A Lagrum has to point at a Kapitel in the Forfattning.")
    private String kapitel;

    @NotNull( message = "A Lagrum has to point at a Paragraf in a Kapitel in a Forfattning.")
    private String paragraf;

    @Nullable
    private String stycke;

    @Nullable
    private String punkt;

    @NotNull( message = "A Lagrum has to have a Giltighetstid.")
    private Period giltighetstid;

}
