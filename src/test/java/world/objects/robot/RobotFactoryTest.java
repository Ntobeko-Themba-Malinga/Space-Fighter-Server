package world.objects.robot;

import org.junit.jupiter.api.Test;
import world.IWorld;
import world.objects.Position;

import static org.junit.jupiter.api.Assertions.*;

class RobotFactoryTest {

    @Test
    void createRobot() {
        Robot robot = RobotFactory.createRobot(
                "TANK",
                new Position(0, 0),
                IWorld.Direction.EAST
        );

        RobotFactory.setRobotSize(4);
        assertEquals(TankRobot.class, robot.getClass());
        assertEquals(new Position(-2, 2), robot.getTopLeftCorner());
        assertEquals(new Position(2, -2), robot.getBottomRightCorner());
        assertEquals(IWorld.Direction.EAST, robot.getDirection());
    }
}