package ro.uaic.info.game.objects.player;

import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.engine.sprite.AssetList;
import ro.uaic.info.engine.sprite.SpriteLoader;
import ro.uaic.info.game.objects.ship.Ship;
import ro.uaic.info.game.objects.weapon.Gun;
import ro.uaic.info.game.objects.weapon.pattern.IonPattern;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player implements GameObject {
    private Ship ship;
    private Transform transform;
    private PlayerInputListener listener;

    private Gun playerBlaster;

    public Player setPlayerBlaster(Gun playerBlaster) {
        this.playerBlaster = playerBlaster;

        return this;
    }

    public PlayerInputListener getInputListener(){
        return this.listener;
    }

    public void update() {
        this.movePlayer();
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
    public BufferedImage getSprite() {
        return this.ship.getSprite();
    }

    public static class PlayerBuilder {
        private Ship ship = new Ship().setSprite(SpriteLoader.getInstance().getAsset(AssetList.PH_SHIP_1));

        public PlayerBuilder withShip(Ship ship) {
            this.ship = (Ship)ship.copy();
            return this;
        }

        public Player build() {
            Player player = new Player();

            player.ship = this.ship;
            player.transform = this.ship.getTransform();
            player.listener = new PlayerInputListener();

            player.playerBlaster = new Gun.GunBuilder().withShooter(player).withPattern(new IonPattern()).build();

            return player;
        }
    }

    public void setTransform(Transform transform) {
        this.ship.setTransform(transform);
    }

    public Ship getShip() {
        return ship;
    }

    public Rectangle getMesh() {
        return this.ship.getMesh();
    }

    @Override
    public Transform getTransform() {
        return this.ship.getTransform();
    }

    @Override
    public GameObject copy() {
        return null;
    }

    private void movePlayer(){
        //this.transform.treatMovementInput(this.listener)

        Double3 appliedForce = new Double3(0.0, 0.0, 0.0);

        if(this.listener.pressedLeft())
            appliedForce.setX( appliedForce.getX() - this.ship.getMovementSpeed().getX() );
        if(this.listener.pressedRight())
            appliedForce.setX( appliedForce.getX() + this.ship.getMovementSpeed().getX() );
        if(this.listener.pressedUp())
            appliedForce.setY( appliedForce.getY() - this.ship.getMovementSpeed().getY() );
        if(this.listener.pressedDown())
            appliedForce.setY( appliedForce.getY() + this.ship.getMovementSpeed().getY() );

        if(this.listener.pressedFireWeapon())
            this.playerBlaster.fire();

        this.transform.push(appliedForce);
    }

    @Override
    public String getLabel() {
        return GameObject.PLAYER_LABEL;
    }
}
