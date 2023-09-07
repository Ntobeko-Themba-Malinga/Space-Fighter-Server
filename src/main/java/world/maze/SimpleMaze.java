package world.maze;

import world.objects.Asteroid;
import world.objects.Position;

import java.util.ArrayList;
import java.util.List;

public class SimpleMaze implements IMaze {
    private List<Asteroid> asteroids;

    public SimpleMaze() {
        this.asteroids = new ArrayList<>();
    }

    @Override
    public void generateMaze(Position topLeftCorner, Position bottomRightCorner) {
        asteroids.add(new Asteroid(new Position(-10, 10), new Position(10, -10)));
        asteroids.add(new Asteroid(new Position(-1, 1), new Position(0, 0)));
        asteroids.add(new Asteroid(new Position(1, 0), new Position(2, -1)));
    }

    @Override
    public List<Asteroid> getAsteroids() {
        return asteroids;
    }
}
