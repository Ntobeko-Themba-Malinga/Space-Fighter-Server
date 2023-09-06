package world.objects.robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.WorldAllowed;
import util.WorldNotAllowed;
import world.IWorld;
import world.objects.Position;


import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    private IWorld world;

    @BeforeEach
    void setUp() {
        this.world = new WorldAllowed();
    }

    @Test
    void updatePositionNorth() {
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.NORTH);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(0, 8), robot.getTopLeftCorner());
        assertEquals(new Position(6, 2), robot.getBottomRightCorner());
    }

    @Test
    void updatePositionNorthNotAllowed() {
        this.world = new WorldNotAllowed();
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.NORTH);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(0, 6), robot.getTopLeftCorner());
        assertEquals(new Position(6, 0), robot.getBottomRightCorner());
    }

    @Test
    void updatePositionSouth() {
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.SOUTH);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(0, 4), robot.getTopLeftCorner());
        assertEquals(new Position(6, -2), robot.getBottomRightCorner());
    }

    @Test
    void updatePositionSouthNotAllowed() {
        this.world = new WorldNotAllowed();
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.SOUTH);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(0, 6), robot.getTopLeftCorner());
        assertEquals(new Position(6, 0), robot.getBottomRightCorner());
    }

    @Test
    void updatePositionWEST() {
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.WEST);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(-2, 6), robot.getTopLeftCorner());
        assertEquals(new Position(4, 0), robot.getBottomRightCorner());
    }

    @Test
    void updatePositionWESTNotAllowed() {
        this.world = new WorldNotAllowed();
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.WEST);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(0, 6), robot.getTopLeftCorner());
        assertEquals(new Position(6, 0), robot.getBottomRightCorner());
    }

    @Test
    void updatePositionEAST() {
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.EAST);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(2, 6), robot.getTopLeftCorner());
        assertEquals(new Position(8, 0), robot.getBottomRightCorner());
    }

    @Test
    void updatePositionEASTNotAllowed() {
        this.world = new WorldNotAllowed();
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.EAST);

        robot.updatePosition(this.world, 2);
        assertEquals(new Position(0, 6), robot.getTopLeftCorner());
        assertEquals(new Position(6, 0), robot.getBottomRightCorner());
    }

    @Test
    void updateDirectionRight() {
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.NORTH);

        robot.updateDirection(true);
        assertEquals(IWorld.Direction.EAST, robot.getDirection());
    }

    @Test
    void updateDirectionLeft() {
        Robot robot = new TankRobot(new Position(0, 6), new Position(6, 0), IWorld.Direction.NORTH);

        robot.updateDirection(false);
        assertEquals(IWorld.Direction.WEST, robot.getDirection());
    }
}