package server.communication.response;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterSuccessResponse extends Response {
    @Override
    public Map<String, Object> message(Context ctx) {
        ctx.contentType("application/json");
        ctx.status(HttpCode.CREATED);
        Map<String, Object> response = new HashMap<>();
        response.put("result", "created");
        response.put("message", "User successfully created!");
        ctx.json(response);
        return response;
    }
}
