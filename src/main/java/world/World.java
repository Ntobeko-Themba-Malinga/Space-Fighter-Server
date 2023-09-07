package world;

import world.maze.IMaze;
import world.maze.SimpleMaze;
import world.objects.Position;
import world.objects.robot.Robot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World implements IWorld {
    private final Position topLeftCorner;
    private final Position bottomRightCorner;
    private IMaze maze;
    private Map<String, Robot> robots;

    public World(IMaze maze, Position topLeftCorner, Position bottomRightCorner) {
        this.maze = maze;
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
        this.robots = new ConcurrentHashMap<>();
    }

    @Override
    public PositionUpdate isPositionAllowed(Position pos) {
        return PositionUpdate.ALLOWED;
    }

    @Override
    public PositionUpdate isPathAllowed(Robot robot, Position pos1, Position pos2) {
        return null;
    }

    @Override
    public boolean addRobot(Robot robot) {
        return false;
    }

    @Override
    public boolean removeRobot(Robot robot) {
        return false;
    }

    @Override
    public boolean isRobotExist(String name) {
        return false;
    }

    @Override
    public Map<String, Robot> getRobots() {
        return null;
    }

    @Override
    public void setRobots(Map<String, Robot> robots) {
        this.robots = robots;
    }

    @Override
    public Position getTopLeftCorner() {
        return null;
    }

    @Override
    public Position getBottomRightCorner() {
        return null;
    }
}
