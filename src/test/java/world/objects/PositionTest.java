package world.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void position() {
        Position pos = new Position(1, 2);

        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
    }

    @Test
    void equalsTrue() {
        Position pos1 = new Position(0, 1);
        Position pos2 = new Position(0, 1);

        assertEquals(pos1, pos2);
    }

    @Test
    void equalsFalse() {
        Position pos1 = new Position(0, 1);
        Position pos2 = new Position(1, 0);

        assertNotEquals(pos1, pos2);
    }
}