package se.fk.mimer.datamodel.v2.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;

@Singleton
@Provider
public class ObjectMapperProducer
{
    @Produces
    public ObjectMapper getObjectMapper()
    {
        return new ObjectMapper()
                .registerModule( new JavaTimeModule() )
                .registerModule( new Jdk8Module() )
                .enable( DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY )
                .configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false )
                .disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );
    }
}
