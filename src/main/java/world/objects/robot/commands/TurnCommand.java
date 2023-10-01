package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.List;

public class TurnCommand extends Command {
    public TurnCommand(JsonNode arguments) {
        super(arguments);
    }

    @Override
    public String execute(IWorld world, String username) {
        JsonNode args = getArguments();
        String argument = args.get(0).asText().toLowerCase();
        Robot robot = world.getRobot(username);

        setResult("ERROR");
        if (robot == null) {
            setMessage("Robot not found!");
        } else if (List.of("left", "right").contains(argument)) {
            robot.updateDirection(argument.equals("right"));
            setResult("OK");
            setMessage("Done");
            setStatus(robot.getProperties());
        } else {
            setMessage("Invalid argument");
        }
        return buildResponse();
    }
}
