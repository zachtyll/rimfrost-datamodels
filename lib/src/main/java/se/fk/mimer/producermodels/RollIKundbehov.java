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


@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class RollIKundbehov
{
    @NotBlank( message = "RollIKundbehov must have a kundid" )
    private String kundid;

    @NotBlank( message = "RollIKundbehov must have a roll" )
    private String roll;

    @NotNull( message = "RollIKundbehov must have an yrkande" )
    private boolean yrkande;

}
