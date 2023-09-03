package server.communication.response;

import org.json.JSONObject;

public class BadRequestResponse extends Response {
    @Override
    public String message() {
        JSONObject response = new JSONObject();
        response.put("status", "ERROR");
        response.put("message", "Bad json format");
        return response.toString();
    }
}
