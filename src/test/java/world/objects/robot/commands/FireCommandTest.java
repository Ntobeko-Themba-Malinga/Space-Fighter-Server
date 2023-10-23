package world.objects.robot.commands;

import org.json.JSONArray;
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

class FireCommandTest {
    private IWorld world;
    private Robot robot;

    @BeforeEach
    void setUp() {
        this.world = new World(new SimpleMaze(), new Position(-100, 100), new Position(100, -100));
        this.robot = RobotFactory.createRobot("TANK", new Position(0,0), IWorld.Direction.NORTH);
        Robot robot2 = RobotFactory.createRobot("TANK", new Position(0,15), IWorld.Direction.WEST);
        this.world.addRobot("TestCrashDummy", this.robot);
        this.world.addRobot("TestCrashDummy2", robot2);
    }

    @Test
    void execute() {
        FireCommand lookCommand = new FireCommand();
        JSONObject results = new JSONObject(lookCommand.execute(this.world, "TestCrashDummy"));

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
        JSONArray hitObjects = results.getJSONArray("hit_object");

        for (Object object : hitObjects) {
            JSONObject hitRobot = (JSONObject) object;
            assertEquals(0, hitRobot.getJSONObject("status").getJSONArray("position").getInt(0));
            assertEquals(15, hitRobot.getJSONObject("status").getJSONArray("position").getInt(1));
        }
    }
}