package world;

import world.maze.IMaze;
import world.objects.Asteroid;
import world.objects.Position;
import world.objects.robot.Robot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World implements IWorld {
    private final int WORLD_VISIBILITY;
    private final Position topLeftCorner;
    private final Position bottomRightCorner;
    private final IMaze maze;
    private Map<String, Robot> robots;

    public World(IMaze maze, Position topLeftCorner, Position bottomRightCorner) {
        this.maze = maze;
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
        this.WORLD_VISIBILITY = (int) (bottomRightCorner.getX() * 0.1);
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

    /**
     * Checks if there isn't a game object that is blocking a position.
     * @param pos The position to check.
     * @return ALLOWED enum if the position is allowed else an appropriate enum.
     */
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

    /**
     * Checks if there isn't a game object that is blocking a path.
     * @param pos1 f
     * @return ALLOWED enum if the position is allowed else an appropriate enum.
     */
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
        robotName = robotName.toUpperCase();
        if (this.robots.containsKey(robotName))
            return false;

        this.robots.put(robotName, robot);
        return true;
    }

    @Override
    public boolean removeRobot(String robotName) {
        if (this.robots.containsKey(robotName)) {
            this.robots.remove(robotName);
            return true;
        }
        return false;
    }

    @Override
    public boolean isRobotExist(String name) {
        name = name.toUpperCase();
        for (String robot : robots.keySet()) {
            if (robot.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    @Override
    public boolean isWorldFull() {
        return robots.size() > 10;
    }

    @Override
    public Map<String, Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Robot getRobot(String robotName) {
        robotName = robotName.toUpperCase();
        if (robots.containsKey(robotName))
            return robots.get(robotName);
        return null;
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

    @Override
    public int getVisibility() {
        return 0;
    }

    @Override
    public IMaze getMaze() {
        return maze;
    }
}
