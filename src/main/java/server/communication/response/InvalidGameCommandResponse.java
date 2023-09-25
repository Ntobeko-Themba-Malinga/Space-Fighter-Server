package server.communication.response;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

import java.util.HashMap;
import java.util.Map;

public class InvalidGameCommandResponse extends Response {
    @Override
    public Map<String, Object> message(Context ctx) {
        ctx.contentType("application/json");
        ctx.status(HttpCode.OK);
        Map<String, Object> response = new HashMap<>();
        response.put("result", "error");
        response.put("message", "Invalid command!");
        ctx.json(response);
        return response;
    }
}
