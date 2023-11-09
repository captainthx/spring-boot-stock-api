package com.yutsuki.telegram.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.findAndRegisterModules();
    }

    private JsonUtils(){}

    public static String toJson(Object javaObject) {
        try {
            return OBJECT_MAPPER.writeValueAsString(javaObject);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(String.format("Can't resolve from class: %s.", javaObject.getClass()), e);
        }
    }

    public static Map<String, String> toSimpleMap(Object obj) {
        return OBJECT_MAPPER.convertValue(obj, new TypeReference<Map<String, String>>() {});
    }

    public static Map<String, Object> toMap(Object obj) {
        return OBJECT_MAPPER.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }

    public static<T> T convert(Object obj, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(obj, clazz);
    }

    public static byte[] toBytes(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(String.format("Can't resolve from class: %s.", obj.getClass()), e);
        }
    }

    public static String toJsonIfNotNull(Object obj) {
        Map<String, Object> objectMap = toMap(obj);
        objectMap.entrySet().removeIf((entry)-> Objects.isNull(entry.getValue()));
        return toJson(objectMap);
    }

    public static byte[] toBytesIfNotNull(Object obj) {
        Map<String, Object> objectMap = toMap(obj);
        objectMap.entrySet().removeIf((entry)-> Objects.isNull(entry.getValue()));
        return toBytes(objectMap);
    }

    public static <T> T to(String content, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(String.format("Can't resolve json: %s to class: %s.", content, clazz), e);
        }
    }

    public static <T> T to(byte[] content, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(content, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Can't resolve json to class: %s.", clazz), e);
        }
    }

    public static <T> T toGeneric(byte[] content, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(content, typeReference);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Can't resolve byte length: %s to class: %s.",
                    content.length, typeReference.getType().getTypeName()), e);
        }
    }

    public static <T> T toGeneric(String content, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(content, typeReference);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(String.format("Can't resolve json: %s to class: %s.",
                    content, typeReference.getType().getTypeName()), e);
        }
    }

    public static class JsonUnixSerializer extends JsonSerializer<LocalDateTime> {

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(TimeUtils.toSecond(value));
        }
    }

    public static class JsonTimestampSerializer extends JsonSerializer<LocalDateTime> {

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(TimeUtils.toMilli(value));
        }
    }

    public static class JsonUnixDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return TimeUtils.fromUnix(p.getValueAsLong());
        }
    }

    public static class JsonTimestampDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return TimeUtils.fromMilli(p.getValueAsLong());
        }
    }

}
