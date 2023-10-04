package util;

import world.IWorld;
import world.maze.IMaze;
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
    public boolean addRobot(String robotName, Robot robot) {
        return false;
    }

    @Override
    public boolean removeRobot(String robotName) {
        return false;
    }

    @Override
    public boolean isRobotExist(String name) {
        return false;
    }

    @Override
    public boolean isWorldFull() {
        return false;
    }

    @Override
    public Map<String, Robot> getRobots() {
        return null;
    }

    @Override
    public Robot getRobot(String robotName) {
        return null;
    }

    @Override
    public void setRobots(Map<String, Robot> robots) {

    }

    @Override
    public Position getTopLeftCorner() {
        return null;
    }

    @Override
    public Position getBottomRightCorner() {
        return null;
    }

    @Override
    public int getVisibility() {
        return 0;
    }

    @Override
    public IMaze getMaze() {
        return null;
    }
}
