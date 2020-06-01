package ro.uaic.info.game.objects.ship;

import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.transform.Transform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ship implements GameObject {

    private static final double DEFAULT_SHIP_HEALTH = 100;

    private double health;
    private BufferedImage sprite;
    private Shape mesh;
    //private Dimension spriteDimension;

    private Transform transform;

    @Override
    public String toString() {
        return "Ship{" +
                "health=" + health +
                ", sprite=" + sprite +
                ", mesh=" + mesh +
                ", transform=" + transform +
                '}';
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Ship(double health) {
        this.health = health;
        this.transform = new Transform();
    }

    public Ship(Ship s){
        this.sprite = s.sprite;
        this.health = s.health;
        this.mesh = s.mesh;
        this.transform = s.transform;
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
