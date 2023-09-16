package server.commands;

public class CommandNotFound extends Exception {

    public CommandNotFound(String command) {
        super("Robot command '" + command + "' not found!");
    }
}
