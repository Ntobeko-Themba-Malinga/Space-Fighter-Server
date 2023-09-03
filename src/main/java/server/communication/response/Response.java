package server.communication.response;

import world.IWorld;
import world.objects.robot.Robot;

public abstract class Response {
    private IWorld world;
    private Robot robot;

    public Response(IWorld world, Robot robot) {
        this.world = world;
        this.robot = robot;
    }

    public Response() {}

    public abstract String message();
}
