package server.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import server.communication.response.ResponseFactory;
import server.model.IUserRepository;
import server.model.User;
import server.session.Session;
import server.token.TokenGenerator;
import world.IWorld;

import java.util.HashMap;
import java.util.Map;

public class ServerHandler {
    private final IUserRepository userRepository;
    private final ObjectMapper mapper;
    private final IWorld world;

    public ServerHandler(IWorld world, IUserRepository userRepository) {
        this.mapper = new ObjectMapper();
        this.world = world;
        this.userRepository = userRepository;
    }

    private JsonNode parseRequest(Context context) {
        try {
            return mapper.readTree(context.body());
        } catch (JsonProcessingException e) {
            throw new BadRequestResponse();
        }
    }

    public void userRegister(Context context) {
        JsonNode request = parseRequest(context);
        JsonNode username = request.get("username");
        if (username == null) {
            throw new BadRequestResponse();
        }

        String token = TokenGenerator.generate();
        User user = userRepository.register(username.asText(), token);

        context.contentType("application/json");
        Map<String, String> response = new HashMap<>();

        if (user != null) {
            context.status(HttpCode.CREATED);
            response.put("result", "created");
            response.put("token", token);
        } else {
            context.status(HttpCode.OK);
            response.put("result", "error");
            response.put("token", "Username already taken, try again!");
        }
        context.json(response);
    }

    public void userLogin(Context context) {
        JsonNode request = parseRequest(context);
        JsonNode token = request.get("token");

        if (token == null) {
            throw new BadRequestResponse();
        }

        context.contentType("application/json");
        context.status(HttpCode.OK);
        Map<String, String> response = new HashMap<>();

        User user = userRepository.getUser(token.asText());

        if (user != null) {
            Session.login(context, user);
            response.put("result", "ok");
            response.put("message", "Login successful");
        } else {
            response.put("result", "error");
            response.put("message", "Invalid token");
        }
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
}
