package ro.uaic.info.game.objects.weapon.projectile;

import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.engine.sprite.AssetList;
import ro.uaic.info.engine.sprite.SpriteLoader;

import java.util.Timer;

public class IonProjectile extends Projectile {

    // adjust mesh to be in top left of sprite sheet. Document rectangle mesh size around it.
    // width = 8
    // height = 20
    public static final int MESH_HEIGHT = 20;
    public static final int MESH_WIDTH = 8;

    public static final double DEFAULT_VERTICAL_MOVEMENT_SPEED = -25;
    public static final double DEFAULT_VERTICAL_ACCELERATION = 0.5;

    public static final Double3 DEFAULT_MOVEMENT_SPEED = new Double3(0.0, DEFAULT_VERTICAL_MOVEMENT_SPEED, 0.0);
    public static final Double3 DEFAULT_PROJECTILE_ACCELERATION = new Double3(0.0, DEFAULT_VERTICAL_ACCELERATION, 0.0);

    public IonProjectile(Transform initTransform){
        super(DEFAULT_MOVEMENT_SPEED, AssetList.PH_PROJECTILE_1, initTransform);
        super.setAcceleration(DEFAULT_PROJECTILE_ACCELERATION);
    }

    @Override
    public double getWidth() {
        return MESH_WIDTH;
    }

    @Override
    public double getHeight() {
        return MESH_HEIGHT;
    }
}
