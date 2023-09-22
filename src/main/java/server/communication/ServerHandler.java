package server.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import org.json.JSONObject;
import server.communication.response.ResponseFactory;
import server.model.IUserRepository;
import server.token.TokenGenerator;
import world.IWorld;

import java.util.HashMap;
import java.util.Map;

public class ServerHandler {
    private final IUserRepository userRepository;
    private final ObjectMapper mapper;
    private IWorld world;
    private String client;

    public ServerHandler(IWorld world, IUserRepository userRepository) {
        this.mapper = new ObjectMapper();
        this.world = world;
        this.userRepository = userRepository;
    }

    private JsonNode parseRequest(Context context) {
        try {
            JsonNode request = mapper.readTree(context.body());
            return request;
        } catch (JsonProcessingException e) {
            throw new BadRequestResponse();
        }
    }

    public void userRegister(Context context) {
        JsonNode request = parseRequest(context);
        String username = request.get("username").asText();
        if (username == null) {
            throw new BadRequestResponse();
        }

        String token = TokenGenerator.generate();

        context.contentType("application/json");
        context.status(HttpCode.CREATED);
        Map<String, String> response = new HashMap<>();
        response.put("result", "created");
        response.put("token", token);
        context.json(response);
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
