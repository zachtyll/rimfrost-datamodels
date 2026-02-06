package se.fk.mimer.producermodels.v2.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.v2.deserializers.ZonedDateTimeDeserializer;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class Period {
    @NotNull
    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    private ZonedDateTime from;

    @Nullable
    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    private ZonedDateTime tom;
}
