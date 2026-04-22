package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.codec.v1.registry.VariantRegistry;

public final class CodecObjectMappers {

    private final ObjectMapper variantMapper;
    private final ObjectMapper rawMapper;

    public CodecObjectMappers(VariantRegistry registry, TypeRegistry typeRegistry) {
        this.variantMapper = CodecObjectMapperFactory.createVariantMapper(registry, typeRegistry);
        this.rawMapper = CodecObjectMapperFactory.createRawMapper();
    }

    public ObjectMapper variant() {
        return variantMapper;
    }

    public ObjectMapper raw() {
        return rawMapper;
    }
}
