package world.objects.robot.commands;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    private Robot robot2;

    @BeforeEach
    void setUp() {
        this.world = new World(new SimpleMaze(), new Position(-100, 100), new Position(100, -100));
    }

    @ParameterizedTest
    @CsvSource({"0,15, NORTH", "0, -15, SOUTH", "15, 0, EAST", "-15, 0, WEST"})
    void execute(int x, int y, IWorld.Direction direction) {
        this.robot = RobotFactory.createRobot("TANK", new Position(0,0), direction);
        this.world.addRobot("TestCrashDummy", this.robot);
        this.robot2 = RobotFactory.createRobot("TANK", new Position(x, y), IWorld.Direction.WEST);
        this.world.addRobot("TestCrashDummy2", robot2);
        FireCommand lookCommand = new FireCommand();
        JSONObject results = new JSONObject(lookCommand.execute(this.world, "TestCrashDummy"));

        assertEquals("OK", results.getString("result"));
        assertEquals("Hit", results.getString("message"));
        assertEquals("NORMAL", results.getJSONObject("status").get("status"));
        assertEquals(10, results.getJSONObject("status").getInt("max_shield"));
        assertEquals(10, results.getJSONObject("status").getInt("shields"));
        assertEquals(3, results.getJSONObject("status").getInt("max_shots"));
        assertEquals(2, results.getJSONObject("status").getInt("shots"));
        assertEquals(3, results.getJSONObject("status").getInt("reload"));
        assertEquals(20, results.getJSONObject("status").getInt("bullet_distance"));
        assertNotNull(results.getJSONObject("status").getJSONArray("top_left_corner"));
        assertNotNull(results.getJSONObject("status").getJSONArray("bottom_right_corner"));
        JSONArray hitObjects = results.getJSONArray("hit_object");

        for (Object object : hitObjects) {
            JSONObject hitRobot = (JSONObject) object;
            assertEquals(x, hitRobot.getJSONArray("position").getInt(0));
            assertEquals(y, hitRobot.getJSONArray("position").getInt(1));
        }
        assertEquals(10, robot.getShield());
        assertEquals(8, robot2.getShield());
    }
}