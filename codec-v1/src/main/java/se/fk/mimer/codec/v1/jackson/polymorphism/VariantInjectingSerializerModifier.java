package se.fk.mimer.codec.v1.jackson.polymorphism;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import se.fk.mimer.codec.v1.registry.VariantRegistry;

import java.io.Serial;
import java.util.Set;

public class VariantInjectingSerializerModifier extends BeanSerializerModifier
{
    @Serial
    private static final long serialVersionUID = 1L;

    private final VariantRegistry variantRegistry;
    private final Set<Class<?>> polymorphicRoots;

    public VariantInjectingSerializerModifier(VariantRegistry variantRegistry,
                                              Set<Class<?>> polymorphicRoots) {
        this.variantRegistry = variantRegistry;
        this.polymorphicRoots = polymorphicRoots;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public JsonSerializer<?> modifySerializer( SerializationConfig config,
                                               BeanDescription beanDesc,
                                               JsonSerializer<?> serializer) {
        Class<?> beanClass = beanDesc.getBeanClass();

        // Only wrap if it's in one of our polymorphic hierarchies
        boolean inHierarchy = polymorphicRoots.stream().anyMatch( root -> root.isAssignableFrom(beanClass) );
        if (!inHierarchy) return serializer;

        // Only wrap if THIS concrete class actually has a variant mapping
        String variant = variantRegistry.variantForClass( beanClass );
        if (variant == null || variant.isBlank()) return serializer;

        return new VariantInjectingDelegatingSerializer( (JsonSerializer<Object>) serializer, variantRegistry);
    }
}
