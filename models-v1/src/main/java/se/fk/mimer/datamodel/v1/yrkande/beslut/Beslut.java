package se.fk.mimer.datamodel.v1.yrkande.beslut;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.IDTyp;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.SakfragaStallningstagande;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Beslut
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
    @NotNull
    private ZonedDateTime beslutsDatum;
    @NotNull
    private IDTyp beslutsfattare;
    @NotNull
    private final List<Beslutsrad> beslutsrader = new ArrayList<>();

    public Beslutsrad addBeslutsrad( UUID id, int version, Collection<SakfragaStallningstagande> harSakfragastallningstaganden )
    {
        Beslutsrad rad = Beslutsrad.builder()
                .id( id )
                .version( version )
                .harSakfragaStallningstaganden( harSakfragastallningstaganden )
                .build();
        beslutsrader.add( rad );
        return rad;
    }

    public boolean removeBeslutsad( UUID id )
    {
        Optional<Beslutsrad> toRemove = beslutsrader.stream()
                .filter( b -> b.getId().equals( id ) )
                .findFirst();
        if ( toRemove.isPresent() )
        {
            beslutsrader.remove( toRemove.get() );
            return true;
        }
        return false;
    }
}
