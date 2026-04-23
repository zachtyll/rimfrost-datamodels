package se.fk.mimer.datamodel.v1.sakfragastallningstagande.stallningstaganden;

import jakarta.validation.constraints.NotBlank;
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
public class Stallningstagande
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    @NotBlank
    private String beskrivning;
    @NotNull
    private Stallningstagandetyp stallningstagandetyp;
}
