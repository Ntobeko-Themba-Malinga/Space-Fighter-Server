package world.objects.robot.commands;

import org.json.JSONObject;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private List<String> arguments;

    /**
     * Creates instances of commands that don't have arguments.
     */
    public Command() {
        this.arguments = new ArrayList<>();
    }

    /**
     * Creates instances of commands that have arguments.
     * @param arguments
     */
    public Command(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Commands instructions.
     * @param world The world object to use in the execution of the command.
     * @param robot The robot object to use in the execution of the command.
     * @return JSONObject containing the results of the command execution.
     */
    public abstract JSONObject execute(IWorld world, Robot robot);
}
