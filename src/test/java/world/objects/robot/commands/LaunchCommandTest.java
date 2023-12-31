package world.objects.robot.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import world.IWorld;
import world.World;
import world.maze.SimpleMaze;
import world.objects.Position;
import world.objects.robot.Robot;
import world.objects.robot.RobotFactory;
import world.objects.robot.RobotTypes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LaunchCommandTest {
    private IWorld world;

    @BeforeEach
    void setUp() {
        this.world = new World(new SimpleMaze(), new Position(-100, 100), new Position(100, -100));
    }

    @Test
    void execute() throws JsonProcessingException {
        JsonNode args = new ObjectMapper().readTree("[\"Tank\"]");
        LaunchCommand launchCommand = new LaunchCommand(args);
        JSONObject results = new JSONObject(launchCommand.execute(this.world, "TestUser"));

        assertEquals("OK", results.getString("result"));
        assertEquals("Robot successfully launched", results.getString("message"));
        assertEquals("NORMAL", results.getJSONObject("status").getString("status"));
        assertEquals(10, results.getJSONObject("status").getInt("max_shield"));
        assertEquals(10, results.getJSONObject("status").getInt("shields"));
        assertEquals(3, results.getJSONObject("status").getInt("max_shots"));
        assertEquals(3, results.getJSONObject("status").getInt("shots"));
        assertEquals(3, results.getJSONObject("status").getInt("reload"));
        assertEquals(20, results.getJSONObject("status").getInt("bullet_distance"));
    }
}