package se.fk.mimer.producermodels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.fk.mimer.producermodels.deserializers.ZonedDateTimeDeserializer;

import java.time.ZonedDateTime;


@AllArgsConstructor
@NoArgsConstructor( force = true )
@Accessors( chain = true )
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class Period {

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    private ZonedDateTime from;

    @JsonDeserialize( using = ZonedDateTimeDeserializer.class )
    private ZonedDateTime tom;
}
