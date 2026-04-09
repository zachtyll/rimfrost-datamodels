package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import se.fk.mimer.codec.v1.jackson.deserializers.ZonedDateTimeDeserializer;
import se.fk.mimer.codec.v1.registry.VariantRegistry;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class CodecObjectMapperFactory
{
    private final VariantRegistry variantRegistry;

    public static CodecObjectMapperFactory withEmptyVariants() {
        return new CodecObjectMapperFactory( VariantRegistry.empty() );
    }

    public static ObjectMapper createVariantMapper(VariantRegistry registry) {
        ObjectMapper mapper = createBaseMapper();
        mapper.registerModule(new CodecJacksonModule(registry));
        return mapper;
    }

    public static ObjectMapper createRawMapper() {
        return createBaseMapper();
    }

    private static ObjectMapper createBaseMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Java time (Instant, LocalDat, ZonedDateTime, etc.)
        mapper.registerModule( new JavaTimeModule() );

        // Support Optional<T>
        mapper.registerModule( new Jdk8Module() );

        // Override ZonedDateTime parsing: if timezone missing, assume Europe/Stockholm
        SimpleModule timeOverrides = new SimpleModule("codec-v1-time-overrides");
        timeOverrides.addDeserializer( ZonedDateTime.class, new ZonedDateTimeDeserializer() );
        mapper.registerModule( timeOverrides );

        // Ensure only ISO time formats
        mapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );

        return mapper;
    }
}
