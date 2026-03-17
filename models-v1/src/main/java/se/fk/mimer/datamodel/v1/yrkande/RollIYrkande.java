package se.fk.mimer.datamodel.v1.yrkande;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.datamodel.v1.referensdata.yrkande.RollerIYrkande;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Builder
public class RollIYrkande
{
    private String kundid;

    private RollerIYrkande roll;

    @NotNull( message = "RollIYrkande must have an yrkande" )
    @Getter
    private boolean yrkande;

    public Optional<String> getKundid()
    {
        return Optional.ofNullable( kundid );
    }

    public Optional<RollerIYrkande> getRoll()
    {
        return Optional.ofNullable( roll );
    }

}
