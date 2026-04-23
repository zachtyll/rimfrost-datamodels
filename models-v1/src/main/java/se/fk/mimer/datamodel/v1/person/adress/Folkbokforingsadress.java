package se.fk.mimer.datamodel.v1.person.adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Folkbokforingsadress extends Adress
{
    public Folkbokforingsadress( UUID id, int version, String careOf, String utdelningsadress1,
                                 String utdelningsadress2, String postnummer, String postort )
    {
        super( id, version );
        this.careOf = careOf;
        this.utdelningsadress1 = utdelningsadress1;
        this.utdelningsadress2 = utdelningsadress2;
        this.postnummer = postnummer;
        this.postort = postort;
    }

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
