package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.mimer.codec.v1.fixtures.FolkbokforingsadressFixtures;
import se.fk.mimer.codec.v1.jsonld.JsonLdKeys;
import se.fk.mimer.codec.v1.registry.CodecRegistries;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.codec.v1.registry.VariantRegistry;
import se.fk.mimer.datamodel.v1.person.adress.Adress;
import se.fk.mimer.datamodel.v1.person.adress.Folkbokforingsadress;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VariantRoundTripTest
{
    private ObjectMapper variantMapper;

    @BeforeEach
    void setUp()
    {
        VariantRegistry registry = CodecRegistries.createVariantRegistry();
        TypeRegistry typeRegistry = CodecRegistries.createTypeRegistry();
        variantMapper = CodecObjectMapperFactory.createVariantMapper( registry, typeRegistry );
    }

    @Test
    void encode_injects_variant_for_polymorphic_subclass() throws Exception
    {
        Folkbokforingsadress original = FolkbokforingsadressFixtures.createFolkbokforingsadress();

        JsonNode tree = variantMapper.valueToTree( (Adress) original );

        assertTrue( tree.has( "variant" ) );
        assertEquals( "Folkbokforingsadress", tree.get( "variant" ).asText() );
    }

    @Test
    void decode_resolves_subclass_from_type() throws Exception
    {
        ObjectNode tree = variantMapper.createObjectNode();
        String id = UUID.randomUUID().toString();
        tree.put( JsonLdKeys.TYPE, "fk:Folkbokforingsadress" );
        tree.put( "id", id );
        tree.put( "version", 1 );
        tree.put( "careOf", "" );
        tree.put( "utdelningsadress1", "Testvägen 1" );
        tree.put( "utdelningsadress2", "" );
        tree.put( "postnummer", "12312" );
        tree.put( "postort", "Testia" );

        Folkbokforingsadress decoded = variantMapper.treeToValue( tree, Folkbokforingsadress.class );

        assertInstanceOf( Folkbokforingsadress.class, decoded );
        assertEquals( id, ( (Folkbokforingsadress) decoded ).getId().toString() );
        assertEquals( "Testvägen 1", ( (Folkbokforingsadress) decoded ).getUtdelningsadress1() );
        assertEquals( "12312", ( (Folkbokforingsadress) decoded ).getPostnummer() );
        assertEquals( "Testia", ( (Folkbokforingsadress) decoded ).getPostort() );
    }
}
