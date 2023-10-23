package world.objects.robot.commands;

import org.json.JSONObject;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LookCommand extends Command {
   public List<JSONObject> gameObjects;

    public LookCommand() {
        super();
        this.gameObjects = new ArrayList<>();
    }

    private void lookForRobots(IWorld world, String username) {

        Map<String, Robot> worldRobots = world.getRobots();
        for (String worldRobot : world.getRobots().keySet()) {
            Robot wRobot = worldRobots.get(worldRobot);
            if (!worldRobot.equalsIgnoreCase(username)) {
                JSONObject robotProperties = wRobot.getProperties();
                robotProperties.put("name", worldRobot);
                gameObjects.add(robotProperties);
            }
        }
    }

    @Override
    public String execute(IWorld world, String username) {
        Robot robot = world.getRobot(username);

        lookForRobots(world, username);
        setResult("OK");
        setMessage("Done");
        setStatus(robot.getProperties());
        setObjects(gameObjects);
        return buildResponse();
    }
}
