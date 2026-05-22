package se.fk.mimer.datamodel.v1.person.adress;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = Folkbokforingsadress.class, name = "folkbokforingsadress" ),
} )
public abstract class Adress
{
    @NotNull
    private UUID id;
    @NotNull
    private int version;
}
