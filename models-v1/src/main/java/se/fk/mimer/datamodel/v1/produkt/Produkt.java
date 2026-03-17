package se.fk.mimer.datamodel.v1.produkt;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.exceptions.DomainInvariantException;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Erbjudandetyp;
import se.fk.mimer.datamodel.v1.referensdata.produkt.Produktnamn;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor( force = true )
@Accessors( chain = true )
@Setter
@Getter
public class Produkt
{
    @Builder
    public Produkt( UUID id, int revision, Produktnamn produktnamn, RollIProdukt[] roller, @Nullable Map<UUID, se.fk.mimer.datamodel.v1.produkt.Erbjudande> erbjudande)
    {
        this.id = id;
        this.revision = revision;
        this.produktnamn = produktnamn;
        this.roller = roller;
        this.setErbjudande( erbjudande );
    }
    @NotNull
    private UUID id;

    @NotNull
    private int revision;

    @NotNull
    private Produktnamn produktnamn;

    @NotNull
    private RollIProdukt[] roller;

    @Nullable
    @Setter(AccessLevel.NONE)
    private Map<UUID, se.fk.mimer.datamodel.v1.produkt.Erbjudande> erbjudanden;

    private void setErbjudande(Map<UUID, se.fk.mimer.datamodel.v1.produkt.Erbjudande> erbjudande)
    {
        if(erbjudande == null || erbjudande.isEmpty())
        {
            erbjudanden = new HashMap<>();
            return;
        }
        erbjudanden = new HashMap<>(erbjudande);
    }

    public se.fk.mimer.datamodel.v1.produkt.Erbjudande removeErbjudande( UUID erbjudandeId)
    {
        if( erbjudandeId == null)
        {
            throw new DomainInvariantException( "UUID kan inte vara null för detta metodanrop." );
        }
        assert erbjudanden != null;
        se.fk.mimer.datamodel.v1.produkt.Erbjudande erbjudande = erbjudanden.get(erbjudandeId);
        if( erbjudande == null )
        {
            throw new DomainInvariantException( "Inget erbjudande med det angivna IDt kunde hittas." );
        }
        //Loggning?
        erbjudanden.remove( erbjudandeId );
        return erbjudande;
    }

    public se.fk.mimer.datamodel.v1.produkt.Erbjudande addNewErbjudande( UUID erbjudandeId, int version, String erbjudandenamn, Produktnamn produktnamn, Erbjudandetyp erbjudandetyp )
    {
        se.fk.mimer.datamodel.v1.produkt.Erbjudande newErbjudande = new se.fk.mimer.datamodel.v1.produkt.Erbjudande(erbjudandeId, version, erbjudandenamn, produktnamn, erbjudandetyp );
        erbjudanden.put( newErbjudande.getId(), newErbjudande );
        // Loggning?
        return newErbjudande;
    }
}
