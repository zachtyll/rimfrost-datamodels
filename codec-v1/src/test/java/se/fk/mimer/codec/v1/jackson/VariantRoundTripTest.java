package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v1.registry.CodecRegistries;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.codec.v1.registry.VariantRegistry;
import se.fk.mimer.datamodel.v1.person.FysiskPerson;
import se.fk.mimer.datamodel.v1.person.Person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VariantRoundTripTest
{
    private ObjectMapper variantMapper;

    @BeforeEach
    void setUp() {
        VariantRegistry registry = CodecRegistries.createVariantRegistry();
        TypeRegistry typeRegistry = CodecRegistries.createTypeRegistry();
        variantMapper = CodecObjectMapperFactory.createVariantMapper(registry, typeRegistry);
    }
    @Test
    void encode_injects_variant_for_polymorphic_subclass() throws Exception {
        FysiskPerson original = FysiskPerson.builder()
                .personnummer("199204543567")
                .build();

        JsonNode tree = variantMapper.valueToTree((Person) original);

        assertTrue(tree.has("variant"));
        assertEquals("fysiskPerson", tree.get("variant").asText());
    }

    @Test
    void decode_resolves_subclass_from_type() throws Exception {
        ObjectNode tree = variantMapper.createObjectNode();
        tree.put(JsonLdKeys.TYPE, "fk:FysiskPerson");
        tree.put("personnummer", "199204543567");

        Person decoded = variantMapper.treeToValue(tree, Person.class);

        assertInstanceOf(FysiskPerson.class, decoded);
        assertEquals("199204543567", ((FysiskPerson) decoded).getPersonnummer().get());
    }
}
