package se.fk.mimer.producermodels.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import se.fk.mimer.producermodels.Kontouppgift;

import java.io.IOException;
import java.util.List;

public class KontouppgiftDeserializer extends JsonDeserializer<List<Kontouppgift>>
{
    @Override
    public List<Kontouppgift> deserialize( JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        return jp.readValueAs( new TypeReference<List<Kontouppgift>>() {});
    }
}


