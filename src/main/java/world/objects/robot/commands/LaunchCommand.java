package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.List;

public class LaunchCommand extends Command {
    public LaunchCommand(JsonNode arguments) {
        super(arguments);
    }

    @Override
    public String execute(IWorld world, String username) {
        return null;
    }
}
