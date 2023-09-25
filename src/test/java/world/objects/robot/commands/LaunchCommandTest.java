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
    private Robot robot;

    @BeforeEach
    void setUp() {
        this.world = new World(new SimpleMaze(), new Position(-100, 100), new Position(100, -100));
        this.robot = RobotFactory.createRobot("TANK", new Position(0,0), IWorld.Direction.NORTH);
    }

    @Test
    void execute() throws JsonProcessingException {
        JsonNode args = new ObjectMapper().readTree("{ \"arguments\" : [\"Tank\"]}");
        LaunchCommand launchCommand = new LaunchCommand(args);
        JSONObject results = new JSONObject(launchCommand.execute(this.world, "TestUser"));

        assertEquals("OK", results.getString("results"));

    }
}