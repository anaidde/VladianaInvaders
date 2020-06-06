package ro.uaic.info.game.objects.weapon.projectile;

import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.engine.sprite.AssetList;
import ro.uaic.info.engine.sprite.SpriteLoader;
import java.awt.image.BufferedImage;

public abstract class Projectile implements GameObject {
    protected Transform transform;
    protected BufferedImage sprite;
    protected Double3 movementSpeed;

    public static final String PLAYER_SHOOTER_LABEL = "PLAYER";
    public static final String ENEMY_SHOOTER_LABEL = "ENEMY";

    public static final String DEFAULT_SHOOTER_LABEL = PLAYER_SHOOTER_LABEL;

    private String shooterLabel = DEFAULT_SHOOTER_LABEL;

    public String getShooterLabel() {
        return shooterLabel;
    }

    protected Projectile(Double3 movementSpeed, AssetList asset, Transform transform){
        this.movementSpeed = new Double3(movementSpeed);
        this.sprite = SpriteLoader.getInstance().getAsset(asset);
        this.transform = transform.copy();
    }

    @Override
    public void update() {
        Double3 appliedForce = this.movementSpeed;

        this.transform.push(appliedForce);
    }

    protected void setAcceleration(Double3 acceleration){
        this.transform.setVelocityIncrease(acceleration);
    }

    @Override
    public GameObject copy() {
        return null;
    }

    @Override
    public boolean hasCollision() {
        return true;
    }

    @Override
    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    public static Projectile createIon(Transform projectileTransform){
        return new IonProjectile(projectileTransform);
    }

    public static Projectile createEnergy(Transform projectileTransform){
        return new EnergyProjectile(projectileTransform);
    }

    @Override
    public String getLabel() {
        return GameObject.PROJECTILE_LABEL;
    }

    //    private ProjectileType projectileType;
//    private Rectangle mesh;
//    private Point coordinates;
//    private Rectangle dimension;
//
//    public void update() {
//
//    }
//
//    public void draw(Graphics graphics) {
//
//    }
//
//    public ProjectileType getProjectileType() {
//        return projectileType;
//    }
//
//    public Rectangle getMesh() {
//        return mesh;
//    }
//
//    public Point getCoordinates() {
//        return coordinates;
//    }
//
//    public Rectangle getDimension() {
//        return dimension;
//    }
}

