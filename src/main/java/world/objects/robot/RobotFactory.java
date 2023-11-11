package world.objects.robot;

import com.fasterxml.jackson.databind.JsonNode;
import util.JsonFileReader;
import world.IWorld;
import world.objects.Position;

public class RobotFactory {
    private static int robotSize = 4;

    /**
     * Create a robot instance.
     * @param type The type of robot you want to create.
     * @param position The position the robot will take in the world.
     * @param direction The direction the robot will face.
     * @return Instance of a robot.
     */
    public static Robot createRobot(String type, Position position, IWorld.Direction direction) {
        int halfRobotSize = robotSize / 2;
        int topX = position.getX() - halfRobotSize;
        int topY = position.getY() + halfRobotSize;
        int bottomX = position.getX() + halfRobotSize;
        int bottomY = position.getY() - halfRobotSize;
        Robot robot = new Robot(new Position(topX, topY), new Position(bottomX, bottomY), direction);

        JsonNode robotProperties = null;
        JsonNode defaultRobotProperties = null;
        for (JsonNode robot_ : JsonFileReader.read("robots.json").get("types")) {
            if (robot_.get("type").asText().equalsIgnoreCase(type)) robotProperties = robot_;
            if (robot_.get("type").asText().equalsIgnoreCase("NORMAL"))
                defaultRobotProperties = robot_;
        }
        if (robotProperties == null) robotProperties = defaultRobotProperties;

        robot.setMaxShots(robotProperties.get("shots").asInt());
        robot.setMaxShield(robotProperties.get("shield").asInt());
        robot.setReloadTime(robotProperties.get("reload").asInt());
        robot.setBulletTravelDistance(robotProperties.get("bullet_travel_distance").asInt());
        robot.setHitDamage(robotProperties.get("damage").asInt());
        return robot;
    }

    public static void setRobotSize(int robotSize) {
        RobotFactory.robotSize = robotSize;
    }
}
