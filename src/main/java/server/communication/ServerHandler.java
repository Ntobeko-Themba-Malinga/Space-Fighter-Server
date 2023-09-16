package server.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.websocket.WsMessageContext;
import server.communication.response.ResponseFactory;
import world.IWorld;

public class ServerHandler {
    private ObjectMapper mapper;
    private IWorld world;
    private String client;

    public ServerHandler(IWorld world) {
        this.mapper = new ObjectMapper();
        this.world = world;
    }

    /**
     * Takes instruction and creates and executes the proper command.
     * @param context Javalin context contains the request in json form.
     */
    public void handleCommand(Context context) {
        try {
            JsonNode request = mapper.readTree(context.body());
        } catch (JsonProcessingException e) {
            context.contentType("application/json");
            context.status(HttpCode.OK);
            context.json(ResponseFactory.create("bad_request"));
        }
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
