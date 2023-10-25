package world.objects.robot.commands;

import world.IWorld;
import world.objects.robot.Robot;
import world.objects.robot.RobotState;

public class ReloadCommand extends Command {
    @Override
    public String execute(IWorld world, String username) {
        Robot robot = world.getRobot(username);

        setResult("OK");
        setMessage("Done");
        if (robot == null) {
            setMessage("Robot not found!");
        } else {
            new Thread(() -> {
                robot.setStatus(RobotState.RELOAD);
                try {
                    Thread.sleep(3000);
                    robot.reload();
                    robot.setStatus(RobotState.NORMAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        setStatus(robot.getProperties());
        return buildResponse();
    }
}
