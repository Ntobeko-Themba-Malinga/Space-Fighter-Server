package util;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileReaderTest {
    @Test
    public void read() {
        JsonNode result = JsonFileReader.read("TestRobots.json").get("TANK");
        assertEquals(3, result.get("shots").asInt());
        assertEquals(10, result.get("shield").asInt());
        assertEquals(3, result.get("reload").asInt());
        assertEquals(20, result.get("bullet_travel_distance").asInt());
        assertEquals(2, result.get("damage").asInt());
    }
}