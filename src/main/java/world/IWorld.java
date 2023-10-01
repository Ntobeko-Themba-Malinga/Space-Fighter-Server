package world;

import world.objects.Position;
import world.objects.robot.Robot;

import java.util.Map;

public interface IWorld {
    public enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    public enum PositionUpdate {
        ALLOWED,
        OBSTRUCTED_ROBOT,
        OBSTRUCTED_ASTEROID,
        OUTSIDE_WORLD,
        WORLD_FULL
    }

    /**
     * Checks if a position in the world is already occupied.
     * @param pos The position to check.
     * @return true if position is already taken else false.
     */
    public PositionUpdate isPositionAllowed(Position pos);

    public PositionUpdate isPathAllowed(Robot robot, Position pos1, Position pos2);

    public boolean addRobot(String robotName, Robot robot);

    public boolean removeRobot(String robotName);

    public boolean isRobotExist(String name);

    public boolean isWorldFull();

    public Map<String, Robot> getRobots();

    public Robot getRobot(String robotName);

    public void setRobots(Map<String, Robot> robots);

    public Position getTopLeftCorner();

    public Position getBottomRightCorner();

    public int getVisibility();
}
