package server.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.websocket.WsMessageContext;
import server.communication.response.ResponseFactory;
import world.World;

public class ServerHandler {
    private ObjectMapper mapper;
    private World world;
    private String client;

    public ServerHandler(World world) {
        this.mapper = new ObjectMapper();
        this.world = world;
    }

    /**
     * Takes instruction and creates and executes the proper command.
     * @param context Javalin context contains the request in json form.
     */
    public void handleCommand(WsMessageContext context) {
        try {
            JsonNode request = mapper.readTree(context.message());
        } catch (JsonProcessingException e) {
            context.send(ResponseFactory.create("bad_request"));
        }
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
