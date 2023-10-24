package world.objects.robot.commands;

import org.json.JSONObject;
import world.IWorld;
import world.objects.Asteroid;
import world.objects.GameObject;
import world.objects.GameObjectTypes;
import world.objects.Position;
import world.objects.robot.Robot;

import java.util.*;

public class FireCommand extends Command {
    private final PriorityQueue<JSONObject> hitObjects = new PriorityQueue<>(Comparator.comparingInt(gameObject -> gameObject.getInt("distance")));

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

    private int calculateDistance(Position robot, GameObject gameObject) {
        int gObjectX = gameObject.getCenter().getX();
        int gObjectY = gameObject.getCenter().getY();
        int robotX = robot.getX();
        int robotY = robot.getY();

        int xDiff = Math.abs(gObjectX - robotX);
        int yDiff = Math.abs(gObjectY - robotY);

        if (xDiff == 0) return yDiff;
        return xDiff;
    }

    private void determineHitRobots(IWorld world, Robot robot, Position bStart, Position bEnd) {
        Map<String, Robot> worldRobots = world.getRobots();
        for (String robotName : worldRobots.keySet()) {
            Robot worldRobot = worldRobots.get(robotName);

            if (worldRobot != robot && worldRobot.blocksPath(bStart, bEnd)) {
                System.out.println("Robot");
                JSONObject worldRobotProperties = worldRobot.getProperties();
                worldRobotProperties.put("name", robotName);
                worldRobotProperties.put("distance", calculateDistance(bStart, worldRobot));
                hitObjects.offer(worldRobotProperties);
            }
        }
    }

    private void determineHitAsteroids(IWorld world, Position bStart, Position bEnd) {
        for (Asteroid asteroid : world.getMaze().getAsteroids()) {
            if (asteroid.blocksPath(bStart, bEnd)) {
                JSONObject asteroidInfo = asteroid.getGameObjectInfo();
                asteroidInfo.put("distance", calculateDistance(bStart, asteroid));
                hitObjects.offer(asteroidInfo);
            }
        }
    }

    private JSONObject reduceHitRobotShield(IWorld world, String enemyRobotName, Robot robot) {
        Robot enemyRobot = world.getRobot(enemyRobotName);
        enemyRobot.takeDamage(robot.getHitDamage());
        JSONObject enemyRobotProperties = enemyRobot.getProperties();
        enemyRobotProperties.put("name", enemyRobotName);
        return enemyRobotProperties;
    }

    @Override
    public String execute(IWorld world, String username) {
        Robot robot = world.getRobot(username);
        Position bulletStart = world.getRobot(username).getCenter();
        Position bulletEnd = calculateBulletEnd(robot, bulletStart, robot.getBulletTravelDistance());
        determineHitRobots(world, robot, bulletStart, bulletEnd);
        determineHitAsteroids(world, bulletStart, bulletEnd);

        setResult("OK");
        if (hitObjects.isEmpty()) {
            setMessage("Miss");
        } else {
            setMessage("Hit");
            setStatus(robot.getProperties());
            JSONObject hitObject = Objects.requireNonNull(hitObjects.poll());
            if (hitObject.get("type").toString().equalsIgnoreCase(GameObjectTypes.ROBOT.toString()))
                setHitObject(List.of(reduceHitRobotShield(world, hitObject.getString("name"), robot)));
            else
                setHitObject(new ArrayList<>());
        }
        return buildResponse();
    }
}
