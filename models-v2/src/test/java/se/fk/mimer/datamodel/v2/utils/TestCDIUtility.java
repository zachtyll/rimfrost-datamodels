package se.fk.mimer.datamodel.v2.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Naive CDI container system that manages object instances using a static hash map.
 */
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class TestCDIUtility
{
    private static final Map<Class<?>, Object> classInstances = new HashMap<>();

    static
    {
        ObjectMapperProducer objectMapperProducer = new ObjectMapperProducer();

        try( var validatorFactory = Validation.buildDefaultValidatorFactory() )
        {
            register( Validator.class, validatorFactory.getValidator());
        }

        register( ObjectMapper.class, objectMapperProducer.getObjectMapper() );
    }

    public static <T> T inject( Class<T> classToInject )
    {
        Object instance = classInstances.get( classToInject );
        if( classToInject.isInstance( instance ) )
        {
            return classToInject.cast( instance );
        }
        else
        {
            throw new IllegalStateException( "Class <%s> is not injectable. Make sure it is registered with the " +
                    "injection service" );
        }
    }


    public static <T> void register( Class<T> clazz, Object instance )
    {
        if( clazz.isInstance( instance ) )
        {
            classInstances.put( clazz, instance );
        }
        else
        {
            throw new IllegalArgumentException( "Provided object is not an instance of " + clazz.getName() );
        }
    }
}
