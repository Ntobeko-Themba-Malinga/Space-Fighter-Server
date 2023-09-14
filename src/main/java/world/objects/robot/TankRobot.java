package world.objects.robot;

import world.IWorld;
import world.objects.Position;

public class TankRobot extends Robot {
    public TankRobot(Position topLeftCorner, Position bottomRightCorner, IWorld.Direction direction) {
        super(topLeftCorner, bottomRightCorner, direction);
        setMaxShots(3);
        setMaxShield(10);
        setReloadTime(3);
        setBulletTravelDistance(20);
    }
}
