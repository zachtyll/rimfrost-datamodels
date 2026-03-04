package se.fk.mimer.codec.v2.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v2.registry.CodecRegistries;
import se.fk.mimer.codec.v2.registry.VariantRegistry;
import se.fk.mimer.datamodel.v2.person.FysiskPerson;
import se.fk.mimer.datamodel.v2.person.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VariantRoundTripTest
{

    @Test
    void person_subclass_roundtrip_injects_variant_and_decodes_subclass() throws Exception {
        VariantRegistry registry = CodecRegistries.createVariantRegistry();
        ObjectMapper mapper = new CodecObjectMapperFactory( registry ).create();

        FysiskPerson original = FysiskPerson.builder()
                .personnummer( "199204543567" )
                .build();

        JsonNode tree = mapper.valueToTree( (Person) original);

        assertTrue(tree.has("variant"));
        assertEquals( "fysiskPerson", tree.get( "variant" ).asText() );

        Person decoded = mapper.treeToValue( tree, Person.class );

        assertInstanceOf( FysiskPerson.class, decoded );
        assertEquals(original.getPersonnummer(), ((FysiskPerson) decoded).getPersonnummer());
    }
}
