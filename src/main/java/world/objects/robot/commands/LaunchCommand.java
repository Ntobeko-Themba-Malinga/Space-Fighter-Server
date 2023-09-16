package world.objects.robot.commands;

import org.json.JSONObject;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.List;

public class LaunchCommand extends Command {
    public LaunchCommand(List<String> arguments) {
        super(arguments);
    }

    @Override
    public JSONObject execute(IWorld world, Robot robot) {
        return null;
    }
}
