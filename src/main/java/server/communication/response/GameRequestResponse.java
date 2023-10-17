package server.communication.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GameRequestResponse extends Response {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, Object> message(Context ctx) {
        ctx.contentType("application/json");
        ctx.status(HttpCode.OK);
        Map<String, Object> response = new HashMap<>();
        response.put("result", "ok");
        response.put("message", "Command executed");
        try {
            response.put("data", mapper.readTree(getData()));
        } catch (JsonProcessingException e) {
            response.put("data", null);
        }
        ctx.json(response);
        return response;
    }
}
