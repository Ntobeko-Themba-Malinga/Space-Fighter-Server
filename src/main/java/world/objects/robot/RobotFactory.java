package world.objects.robot;

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
        return switch (type.toUpperCase()) {
            case "TANK" -> new TankRobot(new Position(topX, topY), new Position(bottomX, bottomY), direction);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    public static void setRobotSize(int robotSize) {
        RobotFactory.robotSize = robotSize;
    }
}
