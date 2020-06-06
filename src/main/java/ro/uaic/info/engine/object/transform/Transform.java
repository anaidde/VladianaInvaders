package ro.uaic.info.engine.object.transform;


import ro.uaic.info.engine.Engine;
import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.Trigger;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.game.objects.player.PlayerInputListener;
import ro.uaic.info.game.objects.weapon.projectile.Projectile;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

public class Transform {
    private Double3 location;
    private Double3 rotation;
    private Double3 velocity;
    private Double3 scale;
    private Double3 targetVelocity;

    private Double3 velocityIncrease = new Double3( HORIZONTAL_SPEED_INCREASE, VERTICAL_SPEED_INCREASE, 0.0 ); // no z-axis
    private Double3 velocityDecrease = new Double3( HORIZONTAL_SPEED_DECREASE, VERTICAL_SPEED_DECREASE, 0.0 ); // no z-axis
    private Double3 velocityCap      = new Double3( HORIZONTAL_SPEED_CAP     , VERTICAL_SPEED_CAP     , 0.0 );

    private boolean applyFriction = true;

    private static final double HORIZONTAL_SPEED_CAP = 12;
    private static final double VERTICAL_SPEED_CAP = 12;
    private static final double HORIZONTAL_SPEED_INCREASE = 0.25;
    private static final double VERTICAL_SPEED_INCREASE = 0.25;
    private static final double HORIZONTAL_SPEED_DECREASE = 0.15;
    private static final double VERTICAL_SPEED_DECREASE = 0.15;

    public Transform(){
        this.location = new Double3();
        this.rotation = new Double3();
        this.velocity = new Double3();
        this.targetVelocity = new Double3();
        this.scale = new Double3(1,1,1);
    }

    public Transform(Transform obj){
        this.location           = new Double3(obj.location);
        this.rotation           = new Double3(obj.rotation);
        this.velocity           = new Double3(obj.velocity);
        this.scale              = new Double3(obj.scale);
        this.targetVelocity     = new Double3(obj.targetVelocity);
        this.velocityIncrease   = new Double3(obj.velocityIncrease);
        this.velocityDecrease   = new Double3(obj.velocityDecrease);
        this.velocityCap        = new Double3(obj.velocityCap);
        this.applyFriction      = obj.applyFriction;
    }

    public Transform copy(){
        return new Transform(this);
    }

    public void setApplyFriction(boolean applyFriction) {
        this.applyFriction = applyFriction;
    }

    public boolean isApplyFriction() {
        return applyFriction;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "location=" + location +
                ", rotation=" + rotation +
                ", velocity=" + velocity +
                ", scale=" + scale +
                '}';
    }

    public Double3 getScale() {
        return scale;
    }

    public Double3 getLocation() {
        return location;
    }

    public Double3 getRotation() {
        return rotation;
    }

    public Double3 getVelocity() {
        return velocity;
    }

    public Transform setTargetVelocity(Double3 targetVelocity) {
        this.targetVelocity = targetVelocity;
        return this;
    }

    public Transform setVelocityCap(Double3 velocityCap) {
        this.velocityCap = velocityCap;
        return this;
    }

    public Transform setVelocityDecrease(Double3 velocityDecrease) {
        this.velocityDecrease = velocityDecrease;
        return this;
    }

    public Transform setVelocityIncrease(Double3 velocityIncrease) {
        this.velocityIncrease = velocityIncrease;
        return this;
    }

    public Double3 getVelocityCap() {
        return velocityCap;
    }

    public Double3 getVelocityDecrease() {
        return velocityDecrease;
    }

    public Double3 getVelocityIncrease() {
        return velocityIncrease;
    }

    public Double3 getTargetVelocity() {
        return targetVelocity;
    }

    public Transform setScale(Double3 scale) {
        this.scale = scale;
        return this;
    }

    public Transform setScale(double x, double y, double z){
        this.scale = new Double3(x, y, z);
        return this;
    }

    public Transform setLocation(Double3 location) {
        this.location = location;
        return this;
    }

    public Transform setRotation(Double3 rotation) {
        this.rotation = rotation;
        return this;
    }

    public Transform setVelocity(Double3 velocity) {
        this.velocity = velocity;
        return this;
    }

    public Transform setLocation(double x, double y, double z){
        this.location = new Double3(x, y, z);
        return this;
    }

    public Transform setRotation(double x, double y, double z){
        this.rotation = new Double3(x, y, z);
        return this;
    }

    public Transform setVelocity(double x, double y, double z){
        this.velocity = new Double3(x, y, z);
        return this;
    }

    public Transform push(Double3 force){
        this.targetVelocity = force;
        if(!force.isNull())
            this.applyFriction = false;
        return this;
    }

    public Transform push(double x, double y, double z){
        this.targetVelocity = new Double3(x, y, z);
        if( ! (x == 0 && y == 0 && z == 0) )
            this.applyFriction = false;
        return this;
    }

    /**
     * called every frame, applies friction if no push called
     */
    public void calculateVelocity(){
        Double3 v = this.velocity; // actual object's velocity

        if(this.applyFriction){
            /// apply friction to object, enclosen all speeds to 0

//            Double3 vInc = this.velocityIncrease; no need for increase here, friction applies
//            Double3 vCap = this.velocityCap; no need for target or capacity as friction brings the velocity to 0
//            Double3 vTar = this.targetVelocity;
            Double3 vDec = this.velocityDecrease;
//
//            System.out.println(v);
//            System.out.println(vDec);

            this.setVelocity(
                v.getX() == 0 ? 0 : ( v.getX() > 0 ? ( v.getX() <= vDec.getX() ? 0 : v.getX() - vDec.getX() ) : ( -1 * v.getX() <= vDec.getX() ? 0 : v.getX() + vDec.getX() ) ),
                v.getY() == 0 ? 0 : ( v.getY() > 0 ? ( v.getY() <= vDec.getY() ? 0 : v.getY() - vDec.getY() ) : ( -1 * v.getY() <= vDec.getY() ? 0 : v.getY() + vDec.getY() ) ),
                v.getZ() == 0 ? 0 : ( v.getZ() > 0 ? ( v.getZ() <= vDec.getZ() ? 0 : v.getZ() - vDec.getZ() ) : ( -1 * v.getZ() <= vDec.getZ() ? 0 : v.getZ() + vDec.getZ() ) )
            );

        } else {
            this.applyFriction = true; /// set apply friction for next frame, set by update which is called before calculate velocity for object!

            Double3 vInc = this.velocityIncrease;
            Double3 vCap = this.velocityCap;
            Double3 vTar = this.targetVelocity;
            Double3 vDec = this.velocityDecrease; //no need for decrease here, no friction applies

            this.setVelocity(
                vTar.getX() == 0 ? (v.getX() > 0 ? ( v.getX() <= vDec.getX() ? 0 : v.getX() - vDec.getX() ) : ( -1 * v.getX() <= vDec.getX() ? 0 : v.getX() + vDec.getX() )) : ( vTar.getX() > 0 ? Math.min( v.getX() + vInc.getX(), Math.min(vTar.getX(), vCap.getX()) ) : Math.max( v.getX() - vInc.getX(), Math.max( vTar.getX(), -1 * vCap.getX() ) ) ),
                vTar.getY() == 0 ? (v.getY() > 0 ? ( v.getY() <= vDec.getY() ? 0 : v.getY() - vDec.getY() ) : ( -1 * v.getY() <= vDec.getY() ? 0 : v.getY() + vDec.getY() )) : ( vTar.getY() > 0 ? Math.min( v.getY() + vInc.getY(), Math.min(vTar.getY(), vCap.getY()) ) : Math.max( v.getY() - vInc.getY(), Math.max( vTar.getY(), -1 * vCap.getY() ) ) ),
                vTar.getZ() == 0 ? (v.getZ() > 0 ? ( v.getZ() <= vDec.getZ() ? 0 : v.getZ() - vDec.getZ() ) : ( -1 * v.getZ() <= vDec.getZ() ? 0 : v.getZ() + vDec.getZ() )) : ( vTar.getZ() > 0 ? Math.min( v.getZ() + vInc.getZ(), Math.min(vTar.getZ(), vCap.getZ()) ) : Math.max( v.getZ() - vInc.getZ(), Math.max( vTar.getZ(), -1 * vCap.getZ() ) ) )
            );
        }
    }

    public void pushVertical(GameObject o){
        Double3 oldLoc = this.location;
        Double3 v = this.velocity;

        this.setLocation(
            new Double3(
                oldLoc.getX(),
                oldLoc.getY() + v.getY(),
                oldLoc.getZ()
            )
        );

        List<GameObject> colliderObjectList = this.collisionCheck(o);

//        System.out.println(colliderObjectList);

        if(o.hasCollision() && !colliderObjectList.isEmpty() ){
            this.setLocation(oldLoc);
            v.setY(0.0);
//            System.out.println(this.velocity);
        }
    }

    public void pushHorizontal(GameObject o){
        Double3 oldLoc = this.location;
        Double3 v = this.velocity;

        this.setLocation(
            new Double3(
                oldLoc.getX() + v.getX(),
                oldLoc.getY(),
                oldLoc.getZ()
            )
        );

        List<GameObject> colliderObjectList = this.collisionCheck(o);

//        System.out.println(colliderObjectList);

        if(o.hasCollision() && !colliderObjectList.isEmpty() ){
            this.setLocation(oldLoc);
            v.setX(0.0);
//            System.out.println("this is wrong");
        }

    }

    private List<GameObject> collisionCheck(GameObject o){
        Rectangle mesh = o.getMesh();

        List<GameObject> colliders = new ArrayList<>();

        Engine.getInstance().getGameObjects().getAll().forEach(
            e -> {
                if(e.hasCollision() && o != e)
                    if( ! ( o.getLabel().equals(GameObject.PROJECTILE_LABEL) && o.getLabel().equals(e.getLabel()) ) )
                        if(
                            ! (
                                o.getLabel().equals(GameObject.PROJECTILE_LABEL) && e.getLabel().equals(GameObject.PLAYER_LABEL) &&
                                ( (Projectile)o ).getShooterLabel().equals(Projectile.PLAYER_SHOOTER_LABEL)
                                ||
                                e.getLabel().equals(GameObject.PROJECTILE_LABEL) && o.getLabel().equals(GameObject.PLAYER_LABEL) &&
                                ( (Projectile)e ).getShooterLabel().equals(Projectile.PLAYER_SHOOTER_LABEL)
                            )
                        )
                            if (mesh.intersects(e.getMesh()))
                                colliders.add(e);
                     //else {
                        //if ( o.getLabel().equals(GameObject.PROJECTILE_LABEL) && e.getLabel().equals(GameObject.PLAYER_LABEL) )
                    //}
            }
        );

        Engine.getInstance().getGameObjects().getTriggers().forEach(
            e -> {
                if(e.hasCollision()){

                    //GAME WORLD TRIGGER (to not get objects out of bounds)
                    if(e.getLabel().equals(Trigger.WORLD_EDGE)) {
                        if(o.getLabel().equals(GameObject.PROJECTILE_LABEL)){
                            if(!e.getMesh().intersects(mesh)){
                                o.destroy();
                            }
                        } else
                            if (!e.getMesh().contains(mesh))
                                colliders.add(e);
                    }

                    //FOREACH LABEL ADD CASE
                }
            }
        );

        return colliders;
    }

//    public void treatMovementInput(PlayerInputListener listener){
//        if(listener.pressedUp()){
//            this.velocity.setY(Math.max(this.velocity.getY() - VERTICAL_SPEED_INCREASE, -VERTICAL_SPEED_CAP));
//        } else if(this.velocity.getY() < 0) {
//           // if(!listener.pressedDown()){
//                this.velocity.setY(Math.min(this.velocity.getY() + VERTICAL_SPEED_DECREASE , 0));
//            //}
//        }
//        if(listener.pressedDown()){
//            this.velocity.setY(Math.min(this.velocity.getY() + VERTICAL_SPEED_INCREASE, VERTICAL_SPEED_CAP));
//        } else if(this.velocity.getY() > 0) {
//           //if(!listener.pressedUp()){
//                this.velocity.setY(Math.max(this.velocity.getY() - VERTICAL_SPEED_DECREASE, 0));
//            //}
//        }
//        if(listener.pressedLeft()){
//            this.velocity.setX(Math.max(this.velocity.getX() - HORIZONTAL_SPEED_INCREASE, -HORIZONTAL_SPEED_CAP));
//        } else if(this.velocity.getX() < 0) {
//           // if(!listener.pressedRight()){
//                this.velocity.setX(Math.min(this.velocity.getX() + HORIZONTAL_SPEED_DECREASE, 0));
//            //}
//        }
//        if(listener.pressedRight()){
//            this.velocity.setX(Math.min(this.velocity.getX() + HORIZONTAL_SPEED_INCREASE, HORIZONTAL_SPEED_CAP));
//        } else if(this.velocity.getX() > 0) {
//          //  if(!listener.pressedLeft()){
//                this.velocity.setX(Math.max(this.velocity.getX() - HORIZONTAL_SPEED_DECREASE, 0));
//            //}
//        }
//    }
}
