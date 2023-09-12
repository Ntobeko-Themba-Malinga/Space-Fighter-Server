package world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import world.maze.IMaze;
import world.maze.SimpleMaze;
import world.objects.Position;
import world.objects.robot.Robot;
import world.objects.robot.TankRobot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    private IWorld world;

    @BeforeEach
    void setUp() {
        IMaze maze = new SimpleMaze();
        maze.generateMaze(new Position(-100, 100), new Position(100, -100));

        Robot robot = new TankRobot(new Position(-20, -20), new Position(-10, -30), IWorld.Direction.NORTH);
        Map<String, Robot> robots = new ConcurrentHashMap<>();
        robots.put("TestCrashDummy", robot);

        this.world = new World(maze, new Position(-200, 200), new Position(200, -200));
        this.world.setRobots(robots);
    }

    @Test
    void isPositionAllowed() {
        assertEquals(IWorld.PositionUpdate.ALLOWED, this.world.isPositionAllowed(new Position(-10, 12)));
        assertEquals(IWorld.PositionUpdate.OBSTRUCTED_ROBOT, this.world.isPositionAllowed(new Position(-15, -25)));
        assertEquals(IWorld.PositionUpdate.OBSTRUCTED_ASTEROID, this.world.isPositionAllowed(new Position(0, 0)));
        assertEquals(IWorld.PositionUpdate.OUTSIDE_WORLD, this.world.isPositionAllowed(new Position(1000, 0)));
    }

    @Test
    void addRobotTrue() {
        Robot robot = new TankRobot(new Position(-19, -19), new Position(-5, -20), IWorld.Direction.SOUTH);

        assertEquals(1, this.world.getRobots().values().size());
        boolean results = this.world.addRobot("TestCrashDummy2", robot);
        assertTrue(results);
        assertEquals(2, this.world.getRobots().values().size());
    }

    @Test
    void addRobotFalse() {
        Robot robot = new TankRobot(new Position(-19, -19), new Position(-5, -20), IWorld.Direction.SOUTH);

        assertEquals(1, this.world.getRobots().values().size());
        boolean results = this.world.addRobot("TestCrashDummy", robot);
        assertFalse(results);
        assertEquals(1, this.world.getRobots().values().size());
    }

    @Test
    void removeRobotTrue() {
        assertEquals(1, this.world.getRobots().values().size());
        boolean results = this.world.removeRobot("TestCrashDummy");
        assertTrue(results);
        assertEquals(0, this.world.getRobots().values().size());
    }

    @Test
    void removeRobotFalse() {
        assertEquals(1, this.world.getRobots().values().size());
        boolean results = this.world.removeRobot("TestCrashDummyNotExist");
        assertFalse(results);
        assertEquals(1, this.world.getRobots().values().size());
    }
}