package world;

import world.objects.Position;
import world.objects.robot.Robot;

import java.util.Map;

public interface IWorld {

    public enum PositionUpdate {
        ALLOWED,
        OBSTRUCTED_ROBOT,
        OBSTRUCTED_ASTEROID,
        OUTSIDE_WORLD,
        WORLD_FULL
    }

    public PositionUpdate isPositionAllowed(Position pos);

    public PositionUpdate isPathAllowed(Robot robot, Position pos1, Position pos2);

    public boolean addRobot(Robot robot);

    public boolean removeRobot(Robot robot);

    public boolean isRobotExist(String name);

    public Map<String, Robot> getRobots();
}
