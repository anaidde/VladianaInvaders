package ro.uaic.info.game.objects.projectile;

import ro.uaic.info.game.objects.projectile.ProjectileType;

import java.awt.*;

public class Projectile {
    private ProjectileType projectileType;
    private Rectangle mesh;
    private Point coordinates;
    private Rectangle dimension;

    public void update() {

    }

    public void draw(Graphics graphics) {

    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public Rectangle getMesh() {
        return mesh;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Rectangle getDimension() {
        return dimension;
    }
}

