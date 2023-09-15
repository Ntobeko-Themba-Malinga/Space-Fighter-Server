package world;

import world.maze.IMaze;
import world.maze.SimpleMaze;
import world.objects.Asteroid;
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

    /**
     * Checks if a maze blocks a position or a path
     * @param pos1 The starting position of path.
     * @param pos2 The ending position of path.
     * @return true if position or path blocked else false.
     */
    private boolean doMazeBlockPositionOrPath(Position pos1, Position pos2) {
        for (Asteroid asteroid : maze.getAsteroids()) {
            if (asteroid.blocksPosition(pos2) || asteroid.blocksPath(pos1, pos2))
                return true;
        }
        return false;
    }

    /**
     * Checks if a maze blocks a position or a path
     * @param pos1 The starting position of path.
     * @param pos2 The ending position of path.
     * @return true if position or path blocked else false.
     */
    private boolean doRobotsBlockPositionOrPath(Robot robot, Position pos1, Position pos2) {
        for (Robot wRobot : robots.values()) {
            if (wRobot != robot && (wRobot.blocksPath(pos1, pos2) || wRobot.blocksPosition(pos2)))
                return true;
        }
        return false;
    }

    /**
     * Checks if a position is within a world.
     * @param pos The position to check.
     * @return true if within world else false.0
     */
    private boolean isWithinWorld(Position pos) {
        int topX = this.topLeftCorner.getX();
        int topY = this.topLeftCorner.getY();
        int bottomX = this.bottomRightCorner.getX();
        int bottomY = this.bottomRightCorner.getY();
        int posX = pos.getX();
        int posY = pos.getY();
        return (topX < posX && posX < bottomX) && (bottomY < posY && posY < topY);
    }

    @Override
    public PositionUpdate isPositionAllowed(Position pos) {
        if (doMazeBlockPositionOrPath(pos, pos))
            return PositionUpdate.OBSTRUCTED_ASTEROID;

        if (doRobotsBlockPositionOrPath(null, pos, pos))
            return PositionUpdate.OBSTRUCTED_ROBOT;

        if (!isWithinWorld(pos))
            return PositionUpdate.OUTSIDE_WORLD;
        return PositionUpdate.ALLOWED;
    }

    @Override
    public PositionUpdate isPathAllowed(Robot robot, Position pos1, Position pos2) {
        if (doMazeBlockPositionOrPath(pos1, pos2))
            return PositionUpdate.OBSTRUCTED_ASTEROID;

        if (doRobotsBlockPositionOrPath(robot, pos1, pos2))
            return PositionUpdate.OBSTRUCTED_ROBOT;

        if (!isWithinWorld(pos1))
            return PositionUpdate.OUTSIDE_WORLD;
        return PositionUpdate.ALLOWED;
    }

    @Override
    public boolean addRobot(String robotName, Robot robot) {
        if (this.robots.keySet().contains(robotName))
            return false;

        this.robots.put(robotName, robot);
        return true;
    }

    @Override
    public boolean removeRobot(String robotName) {
        if (this.robots.keySet().contains(robotName)) {
            this.robots.remove(robotName);
            return true;
        }
        return false;
    }

    @Override
    public boolean isRobotExist(String name) {
        for (String robot : robots.keySet()) {
            if (robot.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    @Override
    public Map<String, Robot> getRobots() {
        return this.robots;
    }

    @Override
    public void setRobots(Map<String, Robot> robots) {
        this.robots = robots;
    }

    @Override
    public Position getTopLeftCorner() {
        return this.topLeftCorner;
    }

    @Override
    public Position getBottomRightCorner() {
        return this.bottomRightCorner;
    }
}
