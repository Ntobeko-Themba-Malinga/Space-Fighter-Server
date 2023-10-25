package world.objects.robot;

import org.json.JSONObject;
import world.IWorld;
import world.objects.GameObject;
import world.objects.GameObjectTypes;
import world.objects.Position;

import java.util.List;

public abstract class Robot extends GameObject {
    private int maxShots;
    private int maxShield;
    private int bulletTravelDistance;
    private int reloadTime;

    private IWorld.Direction direction;
    private RobotState status;
    private int shield;
    private int shots;
    private int hitDamage;

    public Robot(Position topLeftCorner, Position bottomRightCorner, IWorld.Direction direction) {
        super(topLeftCorner, bottomRightCorner, GameObjectTypes.ROBOT);
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

        if (!IWorld.PositionUpdate.ALLOWED.equals(pathAllowed)) return pathAllowed;

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
     * @param numberSteps The number of steps to move the robot by.
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

    /**
     * Decreases the robot's number of shots when it shoots.
     * @return true if it is not out of bullets.
     */
    public boolean fire() {
        if (this.shots > 0) {
            this.shots--;
            return true;
        }
        return false;
    }

    public void reload() {
        this.shots = maxShots;
    }

    /**
     * Reduces the robot's shield when it takes damage.
     * @param amountOfDamage The amount o
     *
     * @return true if robot is still alive else false
     */
    public boolean takeDamage(int amountOfDamage) {
        this.shield -= amountOfDamage;
        if (this.shield < 0) {
            this.setStatus(RobotState.DEAD);
            return false;
        }
        return true;
    }

    public JSONObject getProperties() {
        JSONObject properties = new JSONObject();
        properties.put("status", status.toString());
        properties.put("max_shield", maxShield);
        properties.put("max_shots", maxShots);
        properties.put("shields", shield);
        properties.put("shots", shots);
        properties.put("reload", reloadTime);
        properties.put("bullet_distance", bulletTravelDistance);
        properties.put("top_left_corner", List.of(getTopLeftCorner().getX(), getTopLeftCorner().getY()));
        properties.put("bottom_right_corner", List.of(getTopLeftCorner().getX(), getTopLeftCorner().getY()));
        properties.put("direction", direction);
        properties.put("position", List.of(getCenter().getX(), getCenter().getY()));
        properties.put("type", GameObjectTypes.ROBOT);
        return properties;
    }

    public int getMaxShots() {
        return maxShots;
    }

    protected void setMaxShots(int maxShots) {
        this.maxShots = maxShots;
        this.shots = maxShots;
    }

    public int getMaxShield() {
        return maxShield;
    }

    protected void setMaxShield(int maxShield) {
        this.maxShield = maxShield;
        this.shield = maxShield;
    }

    public int getBulletTravelDistance() {
        return bulletTravelDistance;
    }

    protected void setBulletTravelDistance(int bulletTravelDistance) {
        this.bulletTravelDistance = bulletTravelDistance;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    protected void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public IWorld.Direction getDirection() {
        return direction;
    }

    public RobotState getStatus() {
        return status;
    }

    public void setStatus(RobotState status) {
        this.status = status;
    }

    public int getShield() {
        return shield;
    }

    public int getShots() {
        return shots;
    }

    public int getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(int hitDamage) {
        this.hitDamage = hitDamage;
    }
}
