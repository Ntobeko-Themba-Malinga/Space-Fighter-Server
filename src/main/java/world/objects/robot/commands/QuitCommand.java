package world.objects.robot.commands;

import world.IWorld;
import world.objects.robot.Robot;

public class QuitCommand extends Command {
    @Override
    public String execute(IWorld world, String username) {
        System.out.println("On quit");
        setResult("OK");
        setMessage("Done");
        if (!world.removeRobot(username)) {
            System.out.println("Should not enter here");
            setMessage("Robot not found!");
        }
        return buildResponse();
    }
}
