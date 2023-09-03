package server.communication.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestResponseTest {

    @Test
    void message() throws JsonProcessingException {
        BadRequestResponse badRequestResponse = new BadRequestResponse();
        JsonNode response = new ObjectMapper().readTree(badRequestResponse.message());

        assertEquals("ERROR", response.get("status").asText());
        assertEquals("Bad json format", response.get("message").asText());
    }
}