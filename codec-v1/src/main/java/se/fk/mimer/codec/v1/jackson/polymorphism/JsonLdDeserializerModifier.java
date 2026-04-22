package se.fk.mimer.codec.v1.jackson.polymorphism;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import se.fk.mimer.codec.v1.registry.TypeRegistry;

import java.io.Serial;

/**
 * Wrappar Jacksons inbyggda deserializer för alla klasser som är registrerade
 * i {@link TypeRegistry} med en {@link TypeBasedDeserializer}, så JSON-LD {@code type}
 * kan läsas och städas bort innan delegering.
 */
public class JsonLdDeserializerModifier extends BeanDeserializerModifier {

    @Serial
    private static final long serialVersionUID = 1L;
    private final TypeRegistry typeRegistry;

    public JsonLdDeserializerModifier(TypeRegistry typeRegistry) {
        this.typeRegistry = typeRegistry;
    }

    @Override
    public JsonDeserializer<?> modifyDeserializer(
            DeserializationConfig config,
            BeanDescription beanDescription,
            JsonDeserializer<?> deserializer
    )
    {
        Class<?> valueClass = resolveValueClass(beanDescription);

        if (!typeRegistry.isRegistered(valueClass)) {
            return deserializer;
        }
        return new TypeBasedDeserializer<>(valueClass, typeRegistry, deserializer);
    }

    private static Class<?> resolveValueClass(BeanDescription beanDescription) {
        Class<?> beanClass = beanDescription.getBeanClass();
        Class<?> enclosing = beanClass.getEnclosingClass();
        if (enclosing == null) {
            return beanClass;
        }
        String name = beanClass.getSimpleName();
        if (name.endsWith("BuilderImpl") || name.endsWith("Builder")) {
            return enclosing;
        }
        return beanClass;
    }
}
