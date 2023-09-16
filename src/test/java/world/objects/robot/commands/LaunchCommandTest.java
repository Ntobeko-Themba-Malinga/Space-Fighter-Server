package world.objects.robot.commands;

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
        this.robot = RobotFactory.createRobot(RobotTypes.Tank, new Position(0,0), IWorld.Direction.NORTH);
    }

    @Test
    void execute() {
        LaunchCommand launchCommand = new LaunchCommand(List.of("Tank"));
        JSONObject results = launchCommand.execute(this.world, this.robot);

        assertEquals("OK", results.getString("results"));

    }
}