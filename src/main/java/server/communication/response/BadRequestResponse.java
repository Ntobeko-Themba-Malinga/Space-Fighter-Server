package server.communication.response;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

import java.util.HashMap;
import java.util.Map;

public class BadRequestResponse extends Response {
    @Override
    public Map<String, Object> message(Context ctx) {
        ctx.contentType("application/json");
        ctx.status(HttpCode.BAD_REQUEST);

        Map<String, Object> response = new HashMap<>();
        response.put("result", "error");
        response.put("message", "Bad json format");
        ctx.json(response);
        return response;
    }
}
