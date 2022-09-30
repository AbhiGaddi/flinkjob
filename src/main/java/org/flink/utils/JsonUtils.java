package org.flink.utils;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T deserialize(String value, Class<T> valueType) throws IOException {
        return mapper.readValue(value, valueType);
    }
}
