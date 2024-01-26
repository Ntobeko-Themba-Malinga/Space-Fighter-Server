package server.communication.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import util.JsonFileReader;

import java.util.HashMap;
import java.util.Map;

public class UserLoginSuccess extends Response {
    @Override
    public Map<String, Object> message(Context ctx) {
        ctx.contentType("application/json");
        ctx.status(HttpCode.OK);
        Map<String, Object> response = new HashMap<>();
        response.put("result", "ok");
        response.put("message", "Login successful");
        try {
            response.put("robots", new ObjectMapper().readTree(JsonFileReader.read("robots.json").toString()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response.put("token", getData());
        ctx.json(response);
        return response;
    }
}
