package server.communication.response;

import io.javalin.http.Context;

import java.util.Map;

public abstract class Response {
    private String data;

    public abstract Map<String, Object> message(Context ctx);

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
