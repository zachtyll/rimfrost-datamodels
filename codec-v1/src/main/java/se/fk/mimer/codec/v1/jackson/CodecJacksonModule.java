package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import se.fk.mimer.codec.v1.jackson.polymorphism.JsonLdDeserializerModifier;
import se.fk.mimer.codec.v1.jackson.polymorphism.VariantInjectingSerializerModifier;
import se.fk.mimer.codec.v1.registry.TypeRegistry;
import se.fk.mimer.codec.v1.registry.VariantRegistry;
import se.fk.mimer.datamodel.v1.person.Person;
import se.fk.mimer.datamodel.v1.produceratresultat.ProduceratResultat;

import java.io.Serial;
import java.util.Set;

public class CodecJacksonModule extends SimpleModule
{
    @Serial
    private static final long serialVersionUID = 1L;

    private final VariantRegistry variantRegistry;
    private final TypeRegistry typeRegistry;

    public CodecJacksonModule(VariantRegistry variantRegistry, TypeRegistry typeRegistry) {
        super("codec-v1-variant-module");
        this.variantRegistry = variantRegistry;
        this.typeRegistry = typeRegistry;
    }

    @Override
    public void setupModule(SetupContext context ) {
        super.setupModule( context );

        // Encode: injicerar variant-fält för polymorfa typer
        context.addBeanSerializerModifier(
                new VariantInjectingSerializerModifier(
                        variantRegistry,
                        Set.of(Person.class, ProduceratResultat.class)
                )
        );

        // Decode: hanterar @type-fältet för registrerade klasser
        context.addBeanDeserializerModifier(
                new JsonLdDeserializerModifier(typeRegistry)
        );
    }
}
