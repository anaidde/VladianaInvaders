package ro.uaic.info.engine.object;

import ro.uaic.info.engine.Engine;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.engine.sprite.AssetList;
import ro.uaic.info.engine.sprite.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public interface GameObject {
    int DEFAULT_SPRITE_WIDTH = 75;
    int DEFAULT_SPRITE_HEIGHT = 75;

    default void draw(Graphics g){
        if(this.isShown()) {
            Transform objectTransform = this.getTransform();

            g.drawImage(
                    SpriteLoader.rotateYSprite(
                            this.getSprite(),
                            objectTransform.
                                    getRotation().
                                    getY()
                    ),
                    (int) objectTransform.getLocation().getX(),
                    (int) objectTransform.getLocation().getY(),
                    (int) (getWidth() * objectTransform.getScale().getX()),
                    (int) (getHeight() * objectTransform.getScale().getZ()),
                    null
            );

            if(Engine.getInstance().getDrawMeshes()){
                g.setColor(Color.GREEN);
                g.drawRect(
                        (int)objectTransform.getLocation().getX(),
                        (int)objectTransform.getLocation().getY(),
                        (int) (getWidth() * objectTransform.getScale().getX()),
                        (int) (getWidth() * objectTransform.getScale().getY())
                );
            }
        }
    }

    default void update(){

    }

    default void realUpdate(){
        if(this.isShown()) {
            update();

            Double3 oldLocation = this.getTransform().getLocation();

            this.getTransform().setLocation(new Double3(
                    oldLocation.getX() + this.getTransform().getVelocity().getX(),
                    oldLocation.getY(),
                    oldLocation.getZ() + this.getTransform().getVelocity().getZ()
            ));

            System.out.println(this.collisionCheck());

            if (this.hasCollision() && !this.collisionCheck().isEmpty()) {
                this.getTransform().setLocation(oldLocation);
                this.getTransform().setVelocity(0 ,this.getTransform().getVelocity().getY(),this.getTransform().getVelocity().getZ()); // TODO : get minimum colliding object distance and reset velocity to that, not 0
            }

            oldLocation = this.getTransform().getLocation();

            this.getTransform().setLocation(new Double3(
                    oldLocation.getX(),
                    oldLocation.getY() + this.getTransform().getVelocity().getY(),
                    oldLocation.getZ() + this.getTransform().getVelocity().getZ()
            ));

            System.out.println(this.collisionCheck());

            if (this.hasCollision() && !this.collisionCheck().isEmpty()) {
                this.getTransform().setLocation(oldLocation);
                this.getTransform().setVelocity(this.getTransform().getVelocity().getX(), 0,this.getTransform().getVelocity().getZ());
            }

        }
    }

    default List<GameObject> collisionCheck(){

        List<GameObject> collidingObjects = new ArrayList<>();

        Engine.getInstance().getGameObjects().getAll().forEach( e -> {
            /*  no equals method here, check only for no intersection with self object ptr  */
            if(e.hasCollision() && this != e){
                    if(this.getMesh().intersects(e.getMesh())) {
                        collidingObjects.add(e);
                    }
                }
            }
        );

        Engine.getInstance().getGameObjects().getTriggers().forEach( e -> {
                if(e.hasCollision() && e.getLabel().equals(Trigger.WORLD_EDGE)){
                    if(!this.getMesh().intersects(e.getMesh()))
                        collidingObjects.add(e);
                }
            }
        );

        return collidingObjects;
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

    default boolean isShown(){
        return true;
    }

    default boolean isHidden(){
        return !this.isShown();
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

    default Rectangle getMesh(){
        return new Rectangle(
            (int)getTransform().getLocation().getX(),
            (int)getTransform().getLocation().getY(),
            (int)(getWidth()*getTransform().getScale().getX()),
            (int)(getHeight()*getTransform().getScale().getX())
        );
    }

    default Transform getTransform(){
        return new Transform();
    }

    GameObject copy();
}
