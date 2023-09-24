package server.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import server.communication.response.Response;
import server.communication.response.ResponseFactory;
import server.communication.response.Responses;
import server.model.IUserRepository;
import server.model.User;
import server.session.Session;
import server.token.TokenGenerator;
import world.IWorld;

import java.util.Objects;


public class ServerHandler {
    private final IUserRepository userRepository;
    private final ObjectMapper mapper;
    private final IWorld world;

    public ServerHandler(IWorld world, IUserRepository userRepository) {
        this.mapper = new ObjectMapper();
        this.world = world;
        this.userRepository = userRepository;
    }

    /**
     * Converts the request body to JSON.
     * @param context Javalin Context class object.
     * @return JsonNode object to access the JSON values.
     */
    private JsonNode parseRequest(Context context) {
        try {
            return mapper.readTree(context.body());
        } catch (JsonProcessingException e) {
            throw new BadRequestResponse();
        }
    }

    /**
     * UserRegister helper method. Its job is to determine which response to send back.
     * It will send a success message if account created successfully
     * @param ctx Javalin Context class object.
     * @param user User object, which will not be null if user created successfully.
     */
    private void userRegisterResponse(Context ctx, User user) {
        if (user != null) {
            Objects.requireNonNull(ResponseFactory.create(Responses.USER_REGISTER_SUCCESS)).message(ctx);
        } else {
            Objects.requireNonNull(ResponseFactory.create(Responses.USER_REGISTER_FAIL)).message(ctx);
        }
    }

    /**
     * Creates a new user account,
     * @param context Javalin context.
     */
    public void userRegister(Context context) {
        JsonNode request = parseRequest(context);
        JsonNode username = request.get("username");
        JsonNode password = request.get("password");

        if (username == null || password == null) {
            Objects.requireNonNull(ResponseFactory.create(Responses.BAD_REQUEST)).message(context);
        } else {
            User user = userRepository.register(username.asText(), password.asText());
            userRegisterResponse(context, user);
        }
    }

    /**
     * UserLogin helper method. Its job is to determine which response to send back.
     * It will send a success message if account was found and logged in.
     * @param ctx Javalin Context class object.
     * @param user User object, which will not be null if user account is found.
     */
    private void userLoginResponse(Context ctx, User user) {
        if (user != null) {
            String token = TokenGenerator.generate();
            Session.login(ctx, user, token);
            Response res = ResponseFactory.create(Responses.USER_LOGIN_SUCCESS);
            res.setData(token);
            res.message(ctx);
        } else {
            Objects.requireNonNull(ResponseFactory.create(Responses.USER_LOGIN_FAIL)).message(ctx);
        }
    }

    /**
     * Logs in an existing user account.
     * @param context Javalin context class object.
     */
    public void userLogin(Context context) {
        JsonNode request = parseRequest(context);
        JsonNode username = request.get("username");
        JsonNode password = request.get("password");

        if (username == null || password == null) {
            Objects.requireNonNull(ResponseFactory.create(Responses.BAD_REQUEST)).message(context);
        } else {
            User user = userRepository.getUser(username.asText(), password.asText());
            userLoginResponse(context, user);
        }
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
            context.json(Objects.requireNonNull(ResponseFactory.create(Responses.BAD_REQUEST)).message(context));
        }
    }
}
