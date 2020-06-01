package ro.uaic.info.engine.object;

import ro.uaic.info.engine.object.transform.Transform;

import java.awt.*;

public class Trigger implements GameObject{

    public static final String WORLD_EDGE = "game_world_box";

    private String label;
    private boolean collides = false;
    private Transform transform;
    private Dimension meshSize; /// static mesh

    public Trigger setTransform(Transform transform) {
        this.transform = transform;
        return this;
    }

    public Trigger setMesh(Dimension meshSize) {
        this.meshSize = meshSize;
        return this;
    }

    public Trigger setCollides(boolean collides) {
        this.collides = collides;
        return this;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public double getHeight() {
        return meshSize.height;
    }

    @Override
    public double getWidth() {
        return meshSize.width;
    }

    @Override
    public boolean hasMesh() {
        return true;
    }

    @Override
    public boolean hasCollision() {
        return collides;
    }

    @Override
    public GameObject copy() {
        return null;
    }

    public Trigger setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getLabel() {
        return label;
    }
}
