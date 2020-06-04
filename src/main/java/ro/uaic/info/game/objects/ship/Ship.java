package ro.uaic.info.game.objects.ship;

import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.engine.space.Double3;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ship implements GameObject {

    private static final double DEFAULT_SHIP_HEALTH = 100;

    private double health;
    private BufferedImage sprite;
//    private Shape mesh;  Deprecated, treated by GameObject. Custom mesh enable with override of GetHeight() and GetWidth()
    private Double3 movementSpeed;

    public static final double DEFAULT_VERTICAL_MOVEMENT_SPEED = 10;
    public static final double DEFAULT_HORIZONTAL_MOVEMENT_SPEED = 10;

    public static final Double3 DEFAULT_MOVEMENT_SPEED = new Double3(DEFAULT_HORIZONTAL_MOVEMENT_SPEED, DEFAULT_VERTICAL_MOVEMENT_SPEED, 0.0);
    //private Dimension spriteDimension;

    private Transform transform;

    public void setMovementSpeed(Double3 movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public Double3 getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "health=" + health +
                ", sprite=" + sprite +
//                ", mesh=" + mesh +
                ", transform=" + transform +
                '}';
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Ship(double health) {
        this.health = health;
        this.transform = new Transform();
        this.movementSpeed = new Double3(DEFAULT_MOVEMENT_SPEED);

    }

    public Ship(Ship s){
        this.sprite = s.sprite;
        this.health = s.health;
//        this.mesh = s.mesh;
        this.transform = s.transform;
        this.movementSpeed = new Double3(DEFAULT_MOVEMENT_SPEED);
    }

    public Ship(){
        this.health = DEFAULT_SHIP_HEALTH;
        this.transform = new Transform();
    }

    public Ship setSprite(BufferedImage bufferedImage){
        this.sprite = bufferedImage;
        return this;
    }

    @Override
    public boolean hasMesh() {
        return true;
    }

    @Override
    public boolean hasCollision() {
        return true;
    }

    @Override
    public boolean hasSprite() {
        return true;
    }

    @Override
    public Transform getTransform() {
        return this.transform;
    }

    public double getHealth() {
        return health;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public GameObject copy() {
        return new Ship(this);
    }

    //    public Dimension getSpriteDimension() {
//        return spriteDimension;
//    }
}
