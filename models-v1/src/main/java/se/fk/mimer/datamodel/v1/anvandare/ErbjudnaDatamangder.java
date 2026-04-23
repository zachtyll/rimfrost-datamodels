package se.fk.mimer.datamodel.v1.anvandare;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Getter
@Setter
@Builder
public class ErbjudnaDatamangder
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
    private se.fk.mimer.datamodel.v1.referensdata.anvandare.ErbjudnaDatamangder erbjudnaDatamangder;
    private ErbjudanDatamangdsgrupper tillhorErbjudanDatamangdsgrupp;

    public Optional<ErbjudanDatamangdsgrupper> getTillhorErbjudanDatamangdsgrupp()
    {
        return Optional.ofNullable( tillhorErbjudanDatamangdsgrupp );
    }
}
