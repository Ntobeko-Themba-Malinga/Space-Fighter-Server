package world.objects.robot;

import world.IWorld;
import world.objects.GameObject;
import world.objects.Position;

public abstract class Robot extends GameObject {
    private IWorld.Direction direction;
    private RobotState status;

    public Robot(Position topLeftCorner, Position bottomRightCorner, IWorld.Direction direction) {
        super(topLeftCorner, bottomRightCorner);
        this.direction = direction;
        this.status = RobotState.NORMAL;
    }

    /**
     * Updates the position of the robot
     * @param numberSteps
     */
    public void updatePosition(int numberSteps) {

    }

    public void updateDirection(boolean right) {

    }

    public IWorld.Direction getDirection() {
        return direction;
    }
}
