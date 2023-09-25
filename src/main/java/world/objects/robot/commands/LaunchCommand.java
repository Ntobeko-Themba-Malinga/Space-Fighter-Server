package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import world.IWorld;
import world.objects.Position;
import world.objects.robot.Robot;
import world.objects.robot.RobotFactory;

import java.util.Random;

public class LaunchCommand extends Command {
    private final Random random;

    public LaunchCommand(JsonNode arguments) {
        super(arguments);
        this.random = new Random();
    }

    private Position createRandomPosition(IWorld world) {
        int worldWidth = world.getBottomRightCorner().getX() - world.getTopLeftCorner().getX();
        int worldHeight = world.getTopLeftCorner().getY() - world.getBottomRightCorner().getY();

        Position pos = new Position(0, 0);

        while (!IWorld.PositionUpdate.ALLOWED.equals(world.isPositionAllowed(pos))
                || !IWorld.PositionUpdate.ALLOWED.equals(world.isPathAllowed(null, pos, pos))) {
            int x = random.nextInt(worldWidth) - (worldWidth/2);
            int y = random.nextInt(worldHeight) - (worldHeight/2);
            pos = new Position(x, y);
        }
        return pos;
    }

    private IWorld.Direction createRandomDirection() {
        IWorld.Direction[] directions = {
                IWorld.Direction.NORTH,
                IWorld.Direction.SOUTH,
                IWorld.Direction.EAST,
                IWorld.Direction.WEST
        };
        return directions[random.nextInt(directions.length)];
    }

    @Override
    public String execute(IWorld world, String username) {
        if (world.isRobotExist(username)) {
            System.out.println("Robot already launched");
            return null;
        }

        if (world.isWorldFull()) {
            System.out.println("World is full");
        }

        Position position = createRandomPosition(world);
        IWorld.Direction direction = createRandomDirection();
        String robotType = getArguments().get(0).asText().toUpperCase();
        Robot robot = RobotFactory.createRobot(robotType, position, direction);
        world.addRobot(username, robot);
        return null;
    }
}
