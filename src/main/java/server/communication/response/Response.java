package server.communication.response;

import io.javalin.http.Context;

import java.util.Map;

public abstract class Response {
    public abstract Map<String, String> message(Context ctx);
}
