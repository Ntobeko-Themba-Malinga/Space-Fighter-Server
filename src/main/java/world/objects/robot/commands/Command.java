package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private JsonNode arguments;

    /**
     * Creates instances of commands that don't have arguments.
     */
    public Command() {
        this.arguments = null;
    }

    /**
     * Creates instances of commands that have arguments.
     * @param arguments
     */
    public Command(JsonNode arguments) {
        this.arguments = arguments;
    }

    /**
     * Commands instructions.
     * @param world The world object to use in the execution of the command.
     * @param username The robot object to use in the execution of the command.
     * @return JSONObject containing the results of the command execution.
     */
    public abstract String execute(IWorld world, String username);

    public JsonNode getArguments() {
        return arguments;
    }
}
