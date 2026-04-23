package se.fk.mimer.datamodel.v1.handlaggning.uppgift;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.handlaggning.uppgift.regelutfall.Regelutfallstyp;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Jacksonized
@Getter
@Setter
@SuperBuilder
public class Uppgift extends Basuppgift
{
    @NotNull
    private ZonedDateTime planeradTillTS;
    private ZonedDateTime utfordTS;
    @NotNull
    private Uppgiftsstatustyp uppgiftsstatus;
    @NotNull
    private FSSAInformationstyp fssaInfo;
    @NotNull
    private Uppgiftsspecifikation avserUppgiftsspecifikation;
    @Setter( AccessLevel.PRIVATE )
    private Regelutfall utfall;
    private String aktivitetID;
    private final List<Uppgiftsdata> underlag = new ArrayList<>();
    private final List<Uppgiftsdata> resultat = new ArrayList<>();

    public Optional<ZonedDateTime> getUtfordTS()
    {
        return Optional.ofNullable( utfordTS );
    }

    public Optional<Regelutfall> getUtfall()
    {
        return Optional.ofNullable( utfall );
    }

    public Optional<String> getAktivitetID()
    {
        return Optional.ofNullable( aktivitetID );
    }

    public boolean addUnderlag( String infoObjId, int infoObjVer, Informationsobjekt infoObj )
    {
        try
        {
            underlag.add( createUppgiftsdata( infoObjId, infoObjVer, infoObj ) );
            return true;
        }
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    public boolean addResultat( String infoObjId, int infoObjVer, Informationsobjekt infoObj )
    {
        try
        {
            resultat.add( createUppgiftsdata( infoObjId, infoObjVer, infoObj ) );
            return true;
        }
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    public boolean addUtfall( Regelutfallstyp regelutfallstyp )
    {
        if( getUtfall().isPresent() )
        {
            throw new RuntimeException( "Ett utfall för denna uppgift existerar redan. Ersätt eller ta bort den gamla" +
                    " innan en ny skapas." );
        }
        try
        {
            this.utfall = createRegelutfall( regelutfallstyp );
            return true;
        }
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    public void updateUtfall( Regelutfallstyp regelutfallstyp )
    {
        this.utfall = createRegelutfall( regelutfallstyp );
    }

    public void removeUtfall()
    {
        if( getUtfall().isPresent() ){
            this.utfall = null;
        }
    }

    public boolean removeUnderlag( String infoObjId )
    {
        Optional<Uppgiftsdata> toRemove = findUppgiftsdata( underlag, infoObjId );
        if( toRemove.isPresent() )
        {
            underlag.remove( toRemove.get() );
            return true;
        }
        return false;
    }

    public boolean removeResultat( String infoObjId )
    {
        Optional<Uppgiftsdata> toRemove = findUppgiftsdata( resultat, infoObjId );
        if( toRemove.isPresent() )
        {
            resultat.remove( toRemove.get() );
            return true;
        }
        return false;
    }

    private Optional<Uppgiftsdata> findUppgiftsdata( Collection<Uppgiftsdata> collection, String infoObjId )
    {
        return collection.stream()
                .filter( u -> u.getInformationsobjektsId().equals( infoObjId ) )
                .findFirst();
    }

    private Uppgiftsdata createUppgiftsdata( String infoObjId, int infoObjVer, Informationsobjekt infoObj )
    {
        return Uppgiftsdata.builder()
                .informationsobjektsId( infoObjId )
                .informationsobjektsVersion( infoObjVer )
                .avserInformationsobjekt( infoObj )
                .build();
    }

    private Regelutfall createRegelutfall( Regelutfallstyp regelutfallstyp )
    {
        return Regelutfall.builder()
                .typ( regelutfallstyp )
                .build();
    }
}
