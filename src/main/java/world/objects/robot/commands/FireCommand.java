package world.objects.robot.commands;

import org.json.JSONObject;
import world.IWorld;
import world.objects.Position;
import world.objects.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public class FireCommand extends Command {
    private List<JSONObject> hitRobots = new ArrayList<>();

    private Position calculateBulletEnd(Robot robot, Position bulletStart, int bulletTravelDistance) {
        int startX = bulletStart.getX();
        int startY = bulletStart.getY();
        return switch (robot.getDirection()) {
            case NORTH -> new Position(startX, startY + bulletTravelDistance);
            case SOUTH -> new Position(startX, startY - bulletTravelDistance);
            case WEST -> new Position(startX - bulletTravelDistance, startY);
            case EAST -> new Position(startX + bulletTravelDistance, startY);
        };
    }

    private void determineHitRobots(IWorld world, Robot robot, Position bStart, Position bEnd) {

    }

    @Override
    public String execute(IWorld world, String username) {
        Robot robot = world.getRobot(username);
        Position bulletStart = world.getRobot(username).getCenter();
        Position bulletEnd = calculateBulletEnd(robot, bulletStart, robot.getBulletTravelDistance());
        determineHitRobots(world, robot, bulletStart, bulletEnd);

        setResult("OK");
        if (hitRobots.isEmpty()) {
            setMessage("Miss");
        } else {
            setMessage("Hit");
            setStatus(robot.getProperties());
            setHitObject(hitRobots);
        }
        return buildResponse();
    }
}
