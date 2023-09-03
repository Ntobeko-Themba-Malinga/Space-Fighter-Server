package world.objects.robot;

import world.IWorld;
import world.objects.GameObject;
import world.objects.Position;

public abstract class Robot extends GameObject {
    private IWorld.Direction direction;
    private RobotState status;
    private int maxShots;
    private int bulletTravelDistance;
    private int reloadTime;
    private int shield;

    public Robot(Position topLeftCorner, Position bottomRightCorner, IWorld.Direction direction) {
        super(topLeftCorner, bottomRightCorner);
        this.direction = direction;
        this.status = RobotState.NORMAL;
    }

    /**
     * Updates the position of the robot
     * @param numberSteps
     */
    public void updatePosition(IWorld world, int numberSteps) {

    }

    /**
     * Changes the direction of the robot.
     * @param right if true turns the robot right else turns the robot left.
     */
    public void updateDirection(boolean right) {
        if (right) {
            this.direction = switch (this.direction) {
                case NORTH -> IWorld.Direction.EAST;
                case EAST -> IWorld.Direction.SOUTH;
                case SOUTH -> IWorld.Direction.WEST;
                default -> IWorld.Direction.NORTH;
            };
        } else {
            this.direction = switch (this.direction) {
                case NORTH -> IWorld.Direction.WEST;
                case WEST -> IWorld.Direction.SOUTH;
                case SOUTH -> IWorld.Direction.EAST;
                default -> IWorld.Direction.NORTH;
            };
        }
    }

    public IWorld.Direction getDirection() {
        return direction;
    }

    public int getMaxShots() {
        return maxShots;
    }

    public int getBulletTravelDistance() {
        return bulletTravelDistance;
    }

    public void setBulletTravelDistance(int bulletTravelDistance) {
        this.bulletTravelDistance = bulletTravelDistance;
    }

    public void setMaxShots(int maxShots) {
        this.maxShots = maxShots;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
}
