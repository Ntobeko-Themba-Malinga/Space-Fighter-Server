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

import static org.junit.jupiter.api.Assertions.*;

class TurnCommandTest {
    private IWorld world;
    private Robot robot;

    @BeforeEach
    void setUp() {
        this.world = new World(new SimpleMaze(), new Position(-100, 100), new Position(100, -100));
        this.robot = RobotFactory.createRobot("TANK", new Position(0,0), IWorld.Direction.NORTH);
        this.world.addRobot("TestCrashDummy", this.robot);
    }

    @Test
    void execute() throws JsonProcessingException {
        JsonNode args = new ObjectMapper().readTree("[\"right\"]");
        TurnCommand forwardCommand = new TurnCommand(args);
        JSONObject results = new JSONObject(forwardCommand.execute(this.world, "TestCrashDummy"));

        assertEquals("OK", results.getString("result"));
        assertEquals("Done", results.getString("message"));
        assertEquals("NORMAL", results.getJSONObject("status").get("status"));
        assertEquals(10, results.getJSONObject("status").getInt("max_shield"));
        assertEquals(10, results.getJSONObject("status").getInt("shields"));
        assertEquals(3, results.getJSONObject("status").getInt("max_shots"));
        assertEquals(3, results.getJSONObject("status").getInt("shots"));
        assertEquals(3, results.getJSONObject("status").getInt("reload"));
        assertEquals(20, results.getJSONObject("status").getInt("bullet_distance"));
        assertNotNull(results.getJSONObject("status").getJSONArray("top_left_corner"));
        assertNotNull(results.getJSONObject("status").getJSONArray("bottom_right_corner"));
        assertNotNull(results.getJSONObject("status").getString("direction"));
    }
}