package se.fk.mimer.datamodel.v1.produkt;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.exceptions.DomainInvariantException;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Produktnamn;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class Erbjudande
{
    @Builder
    public Erbjudande( UUID id, int revision, @Nullable String erbjudandeNamn, Produktnamn produktnamn, Erbjudandetyp erbjudandetyp )
    {
        this.id = id;
        this.revision = revision;
        this.erbjudandeNamn = erbjudandeNamn;
        this.produktnamn = produktnamn;
        this.setErbjudandetyp( erbjudandetyp );
    }
    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @Nullable
    private String erbjudandeNamn;

    @NotNull
    private Produktnamn produktnamn;

    @NotNull
    private Erbjudandetyp erbjudandetyp;

    //ToDo: Bryt ut dessa till egna helper klasser
    private void setErbjudandetyp( Erbjudandetyp erbjudandetyp )
    {
        if( erbjudandetyp != null && produktnamn != null && erbjudandetyp.getProduktnamn() != produktnamn )
        {
            throw new DomainInvariantException("Erbjudande " + erbjudandetyp + " hör till produktnamn " + erbjudandetyp.getProduktnamn() + ", inte " + produktnamn );
        }
        this.erbjudandetyp = erbjudandetyp;
    }
}
