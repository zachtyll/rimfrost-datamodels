package se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.somgerrattentill.ersattning;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.Periodisering;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.SakfragaIHandlaggningen;
import se.fk.mimer.datamodel.v1.sakfragastallningstagande.sakfraga.somgerrattentill.Beloppstyper;
import se.fk.mimer.datamodel.v1.Berakningsgrunder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Ersattning extends SakfragaIHandlaggningen
{

    @NotNull
    private Double ersattningsBelopp;
    @NotNull
    private Beloppstyper beloppsTyp;
    @NotNull
    private Periodisering periodisering;
    @NotNull
    private int omfattningProcent;
    @NotNull
    private Collection<Ersattning> samordnasMedErsattningar;
    private final List<Berakningsgrund> berakningsgrunder = new ArrayList<>();

    public void addBerakningsgrund( Berakningsgrunder berakningsgrund )
    {
        Berakningsgrund nyGrund = Berakningsgrund.builder().berakningsgrunder( berakningsgrund ).build();
        berakningsgrunder.add( nyGrund );
    }

    public boolean removeBerakningsgrund( int index )
    {
        if( index + 1 > berakningsgrunder.size() || index < 0 )
        {
            throw new RuntimeException( "Parameter 'index' not valid." );
        }
        berakningsgrunder.remove( index );
        return true;
    }
}
