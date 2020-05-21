package ro.uaic.info.game.objects.ship;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ship {
    private double health;
    private BufferedImage sprite;
    private Dimension spriteDimension;

    public Ship(double health) {
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public Dimension getSpriteDimension() {
        return spriteDimension;
    }
}
