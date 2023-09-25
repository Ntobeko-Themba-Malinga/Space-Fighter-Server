package server.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.IUserRepository;
import server.model.UserRepository;
import world.IWorld;
import world.World;
import world.maze.SimpleMaze;
import world.objects.Position;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    private IUserRepository userRepository;
    private ObjectMapper mapper;
    private final int port = 6000;
    private Server server;

    @BeforeEach
    void setUp() {
        this.mapper = new ObjectMapper();
        this.userRepository = new UserRepository();
        IWorld world = new World(new SimpleMaze(), new Position(-200, 200), new Position(200, -200));
        ServerHandler serverHandler = new ServerHandler(world, userRepository);
        this.server = new Server(serverHandler);
        this.server.start(port);
    }

    @AfterEach
    void tearDown() {
        this.server.stop();
    }

    @Test
    void userRegister() throws JsonProcessingException {
        HttpResponse<String> response = Unirest.post("http://localhost:" + port)
                .body("{ \"username\" : \"TestCrashDummy\", \"password\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(201, response.getStatus());
        assertEquals("created", responseJson.get("result").asText());
        assertEquals("User successfully created!", responseJson.get("message").asText());
    }

    @Test
    void userRegisterTaken() throws JsonProcessingException {
        this.userRepository.register("TestCrashDummy", "testPass123");
        HttpResponse<String> response = Unirest.post("http://localhost:" + port)
                .body("{ \"username\" : \"TestCrashDummy\", \"password\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("ok", responseJson.get("result").asText());
        assertEquals("Username already taken!", responseJson.get("message").asText());
    }

    @Test
    void userRegisterBadRequest() throws JsonProcessingException {
        this.userRepository.register("TestCrashDummy", "testPass123");
        HttpResponse<String> response = Unirest.post("http://localhost:" + port)
                .body("{ \"usename\" : \"TestCrashDummy\", \"pssword\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(400, response.getStatus());
        assertEquals("error", responseJson.get("result").asText());
        assertEquals("Bad json format", responseJson.get("message").asText());
    }

    @Test
    void userLogin() throws JsonProcessingException {
        this.userRepository.register("TestCrashDummy", "testPass123");
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/login")
                .body("{ \"username\" : \"TestCrashDummy\", \"password\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("ok", responseJson.get("result").asText());
        assertEquals("Login successful", responseJson.get("message").asText());
        assertNotNull(responseJson.get("token"));
    }

    @Test
    void userLoginInvalidAccount() throws JsonProcessingException {
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/login")
                .body("{ \"username\" : \"TestCrashDummy\", \"password\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("ok", responseJson.get("result").asText());
        assertEquals("User not found!", responseJson.get("message").asText());
    }

    @Test
    void userLoginBadRequest() throws JsonProcessingException {
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/login")
                .body("{ \"userame\" : \"TestrashDummy\", \"password\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(400, response.getStatus());
        assertEquals("error", responseJson.get("result").asText());
        assertEquals("Bad json format", responseJson.get("message").asText());
    }

    @Test
    void gameWithoutToken() throws JsonProcessingException {
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/game")
                .body("{ \"command\" : \"forward\", \"arguments\" : [\"testPass123\"] }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("ok", responseJson.get("result").asText());
        assertEquals("Token not found!", responseJson.get("message").asText());
    }

    @Test
    void gameWithInvalidJSON() throws JsonProcessingException {
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/game")
                .body("{ \"comand\" : \"forward\", \"argments\" : [\"testPass123\"], \"token\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("ok", responseJson.get("result").asText());
        assertEquals("Invalid JSON", responseJson.get("message").asText());
    }

    @Test
    void gameInvalidUser() throws JsonProcessingException {
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/game")
                .body("{ \"command\" : \"forward\", \"arguments\" : [\"testPass123\"], \"token\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("ok", responseJson.get("result").asText());
        assertEquals("Invalid user!", responseJson.get("message").asText());
    }

    @Test
    void gameCommandNotFound() throws JsonProcessingException {
        userRepository.register("TestCrashDummy", "testPass123");
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/login")
                .body("{ \"username\" : \"TestCrashDummy\", \"password\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        String token = responseJson.get("token").asText();

        response = Unirest.post("http://localhost:" + port + "/game")
                .body("{ \"command\" : \"gfddrt\", \"arguments\" : [\"10\"], \"token\" : \"" + token + "\" }")
                .asString();
        responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("error", responseJson.get("result").asText());
        assertEquals("Invalid command!", responseJson.get("message").asText());
    }

    @Test
    void userLogout() throws JsonProcessingException {
        userRepository.register("TestCrashDummy", "testPass123");
        HttpResponse<String> response = Unirest.post("http://localhost:" + port + "/login")
                .body("{ \"username\" : \"TestCrashDummy\", \"password\" : \"testPass123\" }")
                .asString();
        JsonNode responseJson = mapper.readTree(response.getBody());
        String token = responseJson.get("token").asText();

        response = Unirest.post("http://localhost:" + port + "/logout")
                .body("{ \"token\" : \"" + token + "\" }")
                .asString();
        responseJson = mapper.readTree(response.getBody());
        assertEquals(200, response.getStatus());
        assertEquals("ok", responseJson.get("result").asText());
        assertEquals("Logout successful", responseJson.get("message").asText());
    }
}