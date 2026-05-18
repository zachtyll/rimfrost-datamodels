package se.fk.mimer.datamodel.v1.person.adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Folkbokforingsadress extends Adress
{
    private String careOf;
    @NotNull
    @NotBlank
    private String utdelningsadress1;
    private String utdelningsadress2;
    @NotNull
    @NotBlank
    private String postnummer;
    @NotNull
    @NotBlank
    private String postort;
}
