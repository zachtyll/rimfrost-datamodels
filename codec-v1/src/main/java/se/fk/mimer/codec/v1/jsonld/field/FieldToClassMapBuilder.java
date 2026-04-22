package se.fk.mimer.codec.v1.jsonld.field;

import se.fk.mimer.codec.v1.api.MimerCodecFactory;
import se.fk.mimer.codec.v1.jsonld.builder.NodeTraverser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Bygger en mappning från JSON-fältnamn -> Java klass via reflektion.
 *
 * <p>
 *     Skannar en samling klasser och letar efter fält vars typ
 *     också finns i samlingen - dessa utgör graf-noder som {@link NodeTraverser}
 *     behöver känna till.
 *
 * <p>
 *     Körs en gång vid uppstart i {@link MimerCodecFactory}
 */
public class FieldToClassMapBuilder {

    private FieldToClassMapBuilder() {}

    public static Map<String, Class<?>> build(
            Collection<Class<?>> registeredClasses
    )
    {
        Map<String, Class<?>> map = new HashMap<>();

        for (Class<?> clazz : registeredClasses)
        {
            for (Field field : getAllFields(clazz))
            {
                Class<?> fieldType = resolvedFieldType(field);
                if (fieldType != null && registeredClasses.contains(fieldType))
                {
                    map.put(field.getName(), fieldType);
                }

            }
        }
        return Map.copyOf(map);
    }

    private static List<Field> getAllFields(Class<?> clazz)
    {
        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;

        while (current != null && current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return fields;
    }

    private static Class<?> resolvedFieldType(Field field) {
        Class<?> type = field.getType();

        if (type.isPrimitive() || type == String.class) {
            return null;
        }
        if (type.isEnum()) {
            return null;
        }

        if (Collection.class.isAssignableFrom(type))
        {
            try {
                ParameterizedType pt = (ParameterizedType) field.getGenericType();
                Type typeArg = pt.getActualTypeArguments()[0];

                if (typeArg instanceof Class<?> argClass) {
                    return argClass;
                }
                return null;
            }
            catch (ClassCastException e) {
                return null;
            }
        }
        return type;
    }
}
