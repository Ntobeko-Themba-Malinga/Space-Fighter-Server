package world.objects;

import org.junit.jupiter.api.Test;
import world.IWorld;
import world.objects.robot.RobotFactory;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void gameObject() {
        GameObject gameObject = new Asteroid(new Position(0, 2), new Position(2, 0));

        assertEquals(new Position(0, 2), gameObject.getTopLeftCorner());
        assertEquals(new Position(2, 0), gameObject.getBottomRightCorner());
        assertEquals(new Position(1, 1), gameObject.getCenter());

        GameObject gameObject1 = RobotFactory.createRobot("TANK", new Position(-3, 6), IWorld.Direction.NORTH);
        assertEquals(new Position(-3, 6), gameObject1.getCenter());
    }

    @Test
    void blocksPositionTrue() {
        GameObject gameObject = new Asteroid(new Position(0, 6), new Position(6, 0));
        Position pos = new Position(3, 3);

        assertTrue(gameObject.blocksPosition(pos));
    }

    @Test
    void blocksPositionFalse() {
        GameObject gameObject = new Asteroid(new Position(0, 6), new Position(6, 0));
        Position pos = new Position(3, 9);

        assertFalse(gameObject.blocksPosition(pos));
    }

    @Test
    void blocksPathTrue() {
        GameObject gameObject = new Asteroid(new Position(0, 8), new Position(8, 0));
        Position pos1 = new Position(4, -2);
        Position pos2 = new Position(4, 10);
        Position pos3 = new Position(-2, 4);
        Position pos4 = new Position(10, 4);

        assertTrue(gameObject.blocksPath(pos1, pos2));
        assertTrue(gameObject.blocksPath(pos2, pos1));
        assertTrue(gameObject.blocksPath(pos3, pos4));
        assertTrue(gameObject.blocksPath(pos4, pos3));
    }

    @Test
    void blocksPathFalse() {
        GameObject gameObject = new Asteroid(new Position(0, 8), new Position(8, 0));
        Position pos1 = new Position(12, -2);
        Position pos2 = new Position(12, 10);
        Position pos3 = new Position(-2, 12);
        Position pos4 = new Position(10, 12);

        assertFalse(gameObject.blocksPath(pos1, pos2));
        assertFalse(gameObject.blocksPath(pos2, pos1));
        assertFalse(gameObject.blocksPath(pos3, pos4));
        assertFalse(gameObject.blocksPath(pos4, pos3));
    }
}