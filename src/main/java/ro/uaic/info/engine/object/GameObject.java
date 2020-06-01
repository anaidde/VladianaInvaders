package ro.uaic.info.engine.object;

import ro.uaic.info.engine.Engine;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.engine.sprite.AssetList;
import ro.uaic.info.engine.sprite.SpriteLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface GameObject {
    int DEFAULT_SPRITE_WIDTH = 75;
    int DEFAULT_SPRITE_HEIGHT = 75;

    default void draw(Graphics g){
        Transform objectTransform = this.getTransform();

        g.drawImage(
            SpriteLoader.rotateYSprite(
                    this.getSprite(),
                    objectTransform.
                            getRotation().
                            getY()
            ),
            (int)objectTransform.getLocation().getX(),
            (int)objectTransform.getLocation().getY(),
            (int) (getWidth() * objectTransform.getScale().getX()),
            (int) (getHeight() * objectTransform.getScale().getZ()),
            null
        );
    }

    default void update(){
        Double3 oldLocation = this.getTransform().getLocation();

        this.getTransform().setLocation(
            oldLocation.getX() + this.getTransform().getVelocity().getX(),
            oldLocation.getY() + this.getTransform().getVelocity().getY(),
            oldLocation.getZ() + this.getTransform().getVelocity().getZ()
        );
    }

    default boolean hasMesh(){
        return false;
    }

    default boolean hasCollision(){
        return false;
    }

    default boolean hasSprite(){
        return false;
    }

    default double getWidth(){
        return DEFAULT_SPRITE_WIDTH;
    }

    default double getHeight(){
        return DEFAULT_SPRITE_HEIGHT;
    }

    // TODO : sprite loader
    default BufferedImage getSprite() {
        return SpriteLoader.getInstance().getAsset(AssetList.UNKNOWN);
    }

    default Shape getMesh(){
        return new Rectangle(
            (int)getTransform().getLocation().getX(),
            (int)getTransform().getLocation().getY(),
            (int)getWidth(),
            (int)getHeight()
        );
    }

    default Transform getTransform(){
        return new Transform();
    }

    GameObject copy();
}
