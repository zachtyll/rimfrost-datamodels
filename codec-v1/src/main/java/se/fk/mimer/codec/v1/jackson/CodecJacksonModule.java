package se.fk.mimer.codec.v1.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import se.fk.mimer.codec.v1.jackson.polymorphism.VariantBasedDeserializer;
import se.fk.mimer.codec.v1.jackson.polymorphism.VariantInjectingSerializerModifier;
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


    public CodecJacksonModule(VariantRegistry variantRegistry) {
        super("codec-v1-variant-module");
        this.variantRegistry = variantRegistry;

        // Decode: base-type deserializers
        addDeserializer( Person.class, new VariantBasedDeserializer<>( Person.class, variantRegistry ) );
        addDeserializer( ProduceratResultat.class, new VariantBasedDeserializer<>( ProduceratResultat.class, variantRegistry ) );

    }

    @Override
    public void setupModule(SetupContext context ) {
        super.setupModule( context );

        // Encode: wrap Jackson's default serializer for conrete subclasses
        context.addBeanSerializerModifier(
                new VariantInjectingSerializerModifier(
                        variantRegistry,
                        Set.of(Person.class, ProduceratResultat.class)
                )
        );
    }

}
