package world.objects.robot.commands;

import org.json.JSONObject;
import world.IWorld;
import world.objects.Asteroid;
import world.objects.Position;
import world.objects.robot.Robot;

import java.util.List;
import java.util.Map;

public class LookCommand extends Command {
   public List<JSONObject> gameObjects;
   private Position topLeftCorner;
   private Position bottomRightCorner;

    public LookCommand() {
        super();
    }

    private void lookForRobots(IWorld world, String username) {
        Map<String, Robot> worldRobots = world.getRobots();
        for (String worldRobot : world.getRobots().keySet()) {
            Robot wRobot = worldRobots.get(worldRobot);
            if (!worldRobot.equalsIgnoreCase(username)
                    && (wRobot.getCenter().isIn(topLeftCorner, bottomRightCorner))) {
                JSONObject robotProperties = wRobot.getProperties();
                robotProperties.put("name", worldRobot);
                gameObjects.add(robotProperties);
            }
        }
    }

    @Override
    public String execute(IWorld world, String username) {
        int visibility = world.getVisibility();
        Robot robot = world.getRobot(username);

        int robotX = robot.getCenter().getX();
        int robotY = robot.getCenter().getY();

        topLeftCorner = new Position(robotX - visibility, robotY + visibility);
        bottomRightCorner = new Position(robotX + visibility, robotY - visibility);

        lookForRobots(world, username);
        setResult("OK");
        setMessage("Done");
        setStatus(robot.getProperties());
        setObjects(gameObjects);
        return buildResponse();
    }
}
