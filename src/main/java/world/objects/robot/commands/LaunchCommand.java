package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import world.IWorld;
import world.objects.Asteroid;
import world.objects.Position;
import world.objects.robot.Robot;
import world.objects.robot.RobotFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LaunchCommand extends Command {
    private final Random random;
    public List<JSONObject> gameObjects;

    public LaunchCommand(JsonNode arguments) {
        super(arguments);
        this.random = new Random();
        this.gameObjects = new ArrayList<>();
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

    private void lookForAsteroid(IWorld world) {
        for (Asteroid asteroid : world.getMaze().getAsteroids()) {
            gameObjects.add(asteroid.getGameObjectInfo());
        }
    }

    private void addWorldProperties(IWorld world) {
        gameObjects.add(world.properties());
    }

    @Override
    public String execute(IWorld world, String username) {
        setResult("ERROR");
        if (world.isRobotExist(username)) {
            setMessage("Robot already launched");
        } else if (world.isWorldFull()) {
            setMessage("World is full");
        } else {
            Position position = createRandomPosition(world);
            IWorld.Direction direction = createRandomDirection();
            String robotType = getArguments().get(0).asText().toUpperCase();
            Robot robot = RobotFactory.createRobot(robotType, position, direction);
            world.addRobot(username, robot);
            lookForAsteroid(world);
            addWorldProperties(world);
            setResult("OK");
            setMessage("Robot successfully launched");
            setStatus(robot.getProperties());
            setObjects(gameObjects);
        }
        return buildResponse();
    }
}
