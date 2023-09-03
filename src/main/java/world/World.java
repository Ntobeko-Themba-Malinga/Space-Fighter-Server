package world;

import world.objects.Position;
import world.objects.robot.Robot;

import java.util.Map;

public class World implements IWorld {
    @Override
    public PositionUpdate isPositionAllowed(Position pos) {
        return null;
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
}
