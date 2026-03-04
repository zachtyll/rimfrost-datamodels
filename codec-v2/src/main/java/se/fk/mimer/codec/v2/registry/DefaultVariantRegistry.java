package se.fk.mimer.codec.v2.registry;

import java.util.Map;

public class DefaultVariantRegistry implements VariantRegistry
{
    private final Map<Class<?>, String> classToVariant;
    private final Map<String, Class<?>> variantToClass;

    public DefaultVariantRegistry(Map<Class<?>, String> classToVariant, Map<String, Class<?>> variantToClass) {
        this.classToVariant = Map.copyOf( classToVariant );
        this.variantToClass = Map.copyOf( variantToClass );
    }

    @Override
    public String variantForClass( Class<?> clazz )
    {
        String variant = classToVariant.get( clazz );
        if (variant == null) {
            throw new IllegalArgumentException("No variant mapping for class: " + clazz.getName());
        }
        return variant;
    }

    @Override
    public <T> Class<? extends T> classForVariant(String variant, Class<T> baseType) {
        if (baseType == null) {
            throw new IllegalArgumentException("baseType must not be null");
        }
        if (variant == null || variant.isBlank()) {
            return null;
        }

        Class<?> implementationClass = variantToClass.get( variant );
        if (implementationClass == null) {
            return null;
        }

        if (!( baseType.isAssignableFrom( implementationClass ))) {
            throw new IllegalStateException("Variant '" + variant + "' maps to " + implementationClass.getName()
            + " which is not assignable to base type " + baseType.getName());
        }
        return implementationClass.asSubclass( baseType );
    }
}
