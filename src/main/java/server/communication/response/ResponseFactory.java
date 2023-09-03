package server.communication.response;

import world.IWorld;
import world.objects.robot.Robot;

public class ResponseFactory {
    public static Response create(String type, IWorld world, Robot robot) {
        return null;
    }

    public static Response create(String type) {
        switch (type.toLowerCase()) {
            case "bad_request" -> {
                return new BadRequestResponse();
            }
        }
        return null;
    }
}
