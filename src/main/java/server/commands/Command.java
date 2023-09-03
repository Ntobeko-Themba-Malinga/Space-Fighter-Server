package server.commands;

import world.World;

public abstract class Command {
    public abstract boolean execute(World world);
}
