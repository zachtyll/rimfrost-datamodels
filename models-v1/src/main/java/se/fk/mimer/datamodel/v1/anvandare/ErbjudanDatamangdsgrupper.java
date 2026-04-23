package se.fk.mimer.datamodel.v1.anvandare;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.referensdata.anvandare.ErbjudnaDatamangdsgrupper;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class ErbjudanDatamangdsgrupper
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    @NotBlank
    private String namn;
    @NotNull
    @NotBlank
    private String beskrivning;
    @NotNull
    private ErbjudnaDatamangdsgrupper erbjudnaDatamangdsgrupper;
}
