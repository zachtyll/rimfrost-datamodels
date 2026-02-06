package se.fk.mimer.producermodels.v2.model.produkt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.exceptions.MimerException;
import se.fk.mimer.producermodels.v2.model.DataObject;

import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class Erbjudande implements DataObject
{
    @Builder
    public Erbjudande( UUID id, int revision, @Nullable String erbjudandeNamn, EProduktnamn produktnamn, EErbjudande erbjudande )
    {
        this.id = id;
        this.revision = revision;
        this.erbjudandeNamn = erbjudandeNamn;
        this.produktnamn = produktnamn;
        this.setErbjudande( erbjudande );
    }
    @JsonDeserialize( using = UUIDDeserializer.class )
    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @Nullable
    private String erbjudandeNamn;

    @NotNull
    private EProduktnamn produktnamn;

    @NotNull
    private EErbjudande erbjudande;

    //ToDo: Bryt ut dessa till egna helper klasser
    private void setErbjudande( EErbjudande erbjudande )
    {
        if( erbjudande != null && produktnamn != null && erbjudande.getProduktnamn() != produktnamn )
        {
            throw new MimerException("Erbjudande " + erbjudande + " hör till produktnamn " + erbjudande.getProduktnamn() + ", inte " + produktnamn );
        }
        this.erbjudande = erbjudande;
    }
}
