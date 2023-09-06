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

    private IWorld.PositionUpdate posUpdate(IWorld world, int numSteps, boolean axis) {
        Position topLeftCorner = this.getTopLeftCorner();
        Position bottomRightCorner = this.getBottomRightCorner();
        Position center = this.getCenter();

        int topX = topLeftCorner.getX();
        int topY = topLeftCorner.getY();
        int bottomX = bottomRightCorner.getX();
        int bottomY = bottomRightCorner.getY();
        int centerX = center.getX();
        int centerY = center.getY();

        Position start = new Position(centerX, centerY);
        Position end = (axis) ? new Position(centerX, centerY + numSteps) :  new Position(centerX + numSteps, centerY);

        IWorld.PositionUpdate pathAllowed = world.isPathAllowed(this, start, end);
        IWorld.PositionUpdate posAllowed = world.isPositionAllowed(end);

        if (!IWorld.PositionUpdate.ALLOWED.equals(pathAllowed)) return pathAllowed;
        if (!IWorld.PositionUpdate.ALLOWED.equals(posAllowed)) return posAllowed;

        if (axis) {
            this.setTopLeftCorner(new Position(topX, topY + numSteps));
            this.setBottomRightCorner(new Position(bottomX, bottomY + numSteps));
        } else {
            this.setTopLeftCorner(new Position(topX + numSteps, topY));
            this.setBottomRightCorner(new Position(bottomX + numSteps, bottomY));
        }
        return IWorld.PositionUpdate.ALLOWED;
    }

    /**
     * Updates the position of the robot
     * @param numberSteps
     */
    public IWorld.PositionUpdate updatePosition(IWorld world, int numberSteps) {
        if (IWorld.Direction.NORTH.equals(this.direction)) {
            return posUpdate(world, numberSteps, true);
        } else if (IWorld.Direction.SOUTH.equals(this.direction)) {
            return posUpdate(world, -numberSteps, true);
        } else if (IWorld.Direction.EAST.equals(this.direction)) {
            return posUpdate(world, numberSteps, false);
        } else {
            return posUpdate(world, -numberSteps, false);
        }
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
