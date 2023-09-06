package util;

import world.IWorld;
import world.objects.Position;
import world.objects.robot.Robot;

import java.util.Map;

public class WorldNotAllowed implements IWorld {
    @Override
    public PositionUpdate isPositionAllowed(Position pos) {
        return PositionUpdate.OBSTRUCTED_ASTEROID;
    }

    @Override
    public PositionUpdate isPathAllowed(Robot robot, Position pos1, Position pos2) {
        return PositionUpdate.OBSTRUCTED_ROBOT;
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
