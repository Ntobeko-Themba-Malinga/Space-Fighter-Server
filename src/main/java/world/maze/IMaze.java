package world.maze;

import world.objects.Asteroid;
import world.objects.Position;

import java.util.List;

public interface IMaze {
    /**
     * Genarates a maze by creating instances of the Asteroid class
     * and arranging them in a specific way.
     * @param topLeftCorner The top and left boundary.
     * @param bottomRightCorner The bottom and right boundary.
     */
    public void generateMaze(Position topLeftCorner, Position bottomRightCorner);

    /**
     * @return List of all generated Asteroid class instances.
     */
    public List<Asteroid> getAsteroids();
}
