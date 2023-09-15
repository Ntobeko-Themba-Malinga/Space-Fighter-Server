package world.objects.robot;

import world.IWorld;
import world.objects.Position;

/**
 * Type of Robot: Tank Robot
 * Shield strength: 10,
 * Number of bullets: 3,
 * Bullet travel distance: 20,
 * Bullet reload time: 3 seconds
 */
public class TankRobot extends Robot {
    public TankRobot(Position topLeftCorner, Position bottomRightCorner, IWorld.Direction direction) {
        super(topLeftCorner, bottomRightCorner, direction);
        setMaxShots(3);
        setMaxShield(10);
        setReloadTime(3);
        setBulletTravelDistance(20);
    }
}
