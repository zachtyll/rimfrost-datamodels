package se.fk.mimer.codec.v1.payload;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class PayloadParser
{
    private final ObjectMapper mapper;

    public JsonNode parse( byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("payload bytes must not be null/empty");
        }
        return mapper.readTree( bytes );
    }
}
