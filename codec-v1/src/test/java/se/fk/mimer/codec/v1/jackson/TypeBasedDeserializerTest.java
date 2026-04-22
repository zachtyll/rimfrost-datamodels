package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.exceptions.DecodeException;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v1.registry.CodecRegistries;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.codec.v1.registry.VariantRegistry;
import se.fk.mimer.datamodel.v1.person.Person;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeBasedDeserializerTest {

    private ObjectMapper variantMapper;

    @BeforeEach
    void setUp() {
        VariantRegistry registry = CodecRegistries.createVariantRegistry();
        TypeRegistry typeRegistry = CodecRegistries.createTypeRegistry();
        variantMapper = CodecObjectMapperFactory.createVariantMapper(registry, typeRegistry);
    }

    @Test
    void throws_when_type_is_missing() {
        ObjectNode tree = variantMapper.createObjectNode();
        tree.put("personnummer", "1992042536567");

        assertThrows(DecodeException.class, () -> variantMapper.treeToValue(tree, Person.class));
    }

    @Test
    void throws_when_type_is_unknown() {
        ObjectNode tree = variantMapper.createObjectNode();
        tree.put(JsonLdKeys.TYPE, "fk:NotARealClass");

        assertThrows(DecodeException.class, () -> variantMapper.treeToValue(tree, Person.class));
    }

    @Test
    void throws_when_type_is_outside_hierarchy() {
        ObjectNode tree = variantMapper.createObjectNode();
        tree.put(JsonLdKeys.TYPE, "fk:Yrkande");

        assertThrows(DecodeException.class, () -> variantMapper.treeToValue(tree, Person.class));
    }

}
