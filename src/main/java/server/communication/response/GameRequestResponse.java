package server.communication.response;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GameRequestResponse extends Response {
    @Override
    public Map<String, Object> message(Context ctx) {
        ctx.contentType("application/json");
        ctx.status(HttpCode.OK);
        Map<String, Object> response = new HashMap<>();
        response.put("result", "ok");
        response.put("message", "Command executed");
        response.put("data", new JSONObject(getData()));
        ctx.json(response);
        return response;
    }
}
