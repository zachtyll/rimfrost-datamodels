package se.fk.mimer.datamodel.v1.handlaggning.uppgift.processbeskrivning;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import se.fk.mimer.datamodel.v1.handlaggning.Handlaggningsspecifikation;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Jacksonized
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AbstraktProcessbeskrivning
{
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String bpmnUrl;
    @NotNull
    @NotBlank
    private String namn;
    @NotNull
    private String beskrivning;
    @NotNull
    @NotEmpty
    private Collection<Handlaggningsspecifikation> avserHandlaggningsspecifikation;
    private final Collection<Aktivitet> harAktiviteter = Collections.emptyList();

    public boolean addAktivitet( String aktivitetId, String namn, String beskrivning )
    {
        try
        {
            harAktiviteter.add( Aktivitet.builder()
                    .aktivitetID( aktivitetId )
                    .namn( namn )
                    .beskrivning( beskrivning )
                    .build() );
            return true;
        }
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    public boolean removeAktivitet( String aktivitetId )
    {
        Optional<Aktivitet> toRemove = harAktiviteter.stream()
                .filter( a -> a.getAktivitetID().equals( aktivitetId ) )
                .findFirst();
        if( toRemove.isPresent() )
        {
            harAktiviteter.remove( toRemove.get() );
            return true;
        }
        return false;
    }
}
