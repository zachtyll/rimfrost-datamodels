package se.fk.mimer.producermodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Optional;


@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Builder
public class RollIKundbehov
{
    private String kundid;

    private String roll;

    @NotNull( message = "RollIKundbehov must have an yrkande" )
    @Getter
    private boolean yrkande;

    public Optional<String> getKundid()
    {
        return Optional.ofNullable( kundid );
    }

    public Optional<String> getRoll()
    {
        return Optional.ofNullable( roll );
    }

}
