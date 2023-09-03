package server.commands;

import world.IWorld;

public abstract class Command {
    public abstract boolean execute(IWorld world);
}
