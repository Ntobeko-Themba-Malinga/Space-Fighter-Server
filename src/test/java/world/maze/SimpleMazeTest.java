package world.maze;

import org.junit.jupiter.api.Test;
import world.objects.Asteroid;
import world.objects.Position;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMazeTest {

    @Test
    void generateMaze() {
        IMaze maze = new SimpleMaze();

        Position topLeftCorner = new Position(-100, 100);
        Position bottomRightCorner = new Position(100, -100);

        maze.generateMaze(topLeftCorner, bottomRightCorner);

        assertEquals(3, maze.getAsteroids().size());
    }
}