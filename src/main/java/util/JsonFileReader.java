package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileReader {
    public static JsonNode read(String fileName) {
        File file = new File(fileName);
        try {
            return new ObjectMapper().readValue(file, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
