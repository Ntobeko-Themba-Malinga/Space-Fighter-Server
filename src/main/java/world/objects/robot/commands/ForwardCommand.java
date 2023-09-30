package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import world.IWorld;
import world.objects.robot.Robot;
import world.objects.robot.RobotState;

public class ForwardCommand extends Command {
    public ForwardCommand(JsonNode arguments) {
        super(arguments);
    }

    @Override
    public String execute(IWorld world, String username) {
        Robot robot = world.getRobot(username);
        JsonNode args = getArguments();

        if (robot == null) {
            setResult("ERROR");
            setMessage("Robot not found!");
        } else if (RobotState.DEAD.equals(robot.getStatus())) {
            setResult("ERROR");
            setMessage("Robot dead!");
        } else {
            try {
                int steps = args.get(0).asInt();
                IWorld.PositionUpdate posUpdateState = robot.updatePosition(world, steps);

                setResult("OK");
                setStatus(robot.getProperties());
                switch (posUpdateState) {
                    case ALLOWED -> setMessage("Done");
                    case OBSTRUCTED_ASTEROID -> setMessage("Asteroid");
                    case OBSTRUCTED_ROBOT -> setMessage("Robot");
                    case OUTSIDE_WORLD -> setMessage("Outside");
                }
            } catch (Exception e) {
                setResult("ERROR");
                setMessage("Invalid argument");
                return buildResponse();
            }
        }
        return buildResponse();
    }
}
