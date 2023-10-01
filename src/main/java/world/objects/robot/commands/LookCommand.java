package world.objects.robot.commands;

import org.json.JSONObject;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.List;

public class LookCommand extends Command {
   public List<JSONObject> gameObjects;

    public LookCommand() {
        super();
    }

    private void lookForRobots(IWorld world, int visibility, Robot robot) {

    }

    private void lookForAsteroid(IWorld world, int visibility) {

    }

    @Override
    public String execute(IWorld world, String username) {
        int visibility = world.getVisibility();
        Robot robot = world.getRobot(username);

        lookForAsteroid(world, visibility);
        lookForRobots(world, visibility, robot);
        setResult("OK");
        setMessage("Done");
        setStatus(robot.getProperties());
        setObjects(gameObjects);
        return buildResponse();
    }
}
