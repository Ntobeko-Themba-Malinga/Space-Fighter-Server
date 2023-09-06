package util;

import world.IWorld;
import world.objects.Position;
import world.objects.robot.Robot;

import java.util.Map;

public class WorldAllowed implements IWorld {
    @Override
    public PositionUpdate isPositionAllowed(Position pos) {
        return PositionUpdate.ALLOWED;
    }

    @Override
    public PositionUpdate isPathAllowed(Robot robot, Position pos1, Position pos2) {
        return PositionUpdate.ALLOWED;
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
