package demo.accountmanagementsystem.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializationHelper {
    private static final Logger logger = LoggerFactory.getLogger(SerializationHelper.class);

    public static String toJson(Object object) {
        var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Unable to serialize object", e);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            logger.error("Unable to deserialize to object", e);
            return null;
        }
    }

    public static <T> T byTypeReference(String json, TypeReference<T> valueType) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            logger.error("Unable to deserialize to object", e);
            return null;
        }
    }
}
