package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import server.commands.CommandNotFound;


public class CommandFactory {

    /**
     * Creates a robot command based on given instruction.
     * @param command The instruction for which command to create.
     * @param arguments The arguments of the command.
     * @return A robot command instance object.
     * @throws CommandNotFound thrown when a command is not available.
     */
    public static Command create(String command, JsonNode arguments) throws CommandNotFound {
        return switch (command) {
            case "launch" -> new LaunchCommand(arguments);
            case "forward" -> new ForwardCommand(arguments);
            case "turn" -> new TurnCommand(arguments);
            case "look" -> new LookCommand();
            case "fire" -> new FireCommand();
            case "reload" -> new ReloadCommand();
            case "quit" -> new QuitCommand();
            default -> throw new CommandNotFound(command + " " + arguments);
        };
    }
}
