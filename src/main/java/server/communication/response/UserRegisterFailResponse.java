package server.communication.response;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterFailResponse extends Response {
    @Override
    public Map<String, String> message(Context ctx) {
        ctx.contentType("application/json");
        ctx.status(HttpCode.OK);
        Map<String, String> response = new HashMap<>();
        response.put("result", "ok");
        response.put("message", "Username already taken!");
        ctx.json(response);
        return response;
    }
}
