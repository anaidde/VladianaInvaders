package ro.uaic.info.engine.object.transform;


import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.game.objects.player.PlayerInputListener;

public class Transform {
    private Double3 location;
    private Double3 rotation;
    private Double3 velocity;
    private Double3 scale;

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
        this.scale = new Double3(1,1,1);
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

    public void treatMovementInput(PlayerInputListener listener){
        if(listener.pressedUp()){
            this.velocity.setY(Math.max(this.velocity.getY() - VERTICAL_SPEED_INCREASE, -VERTICAL_SPEED_CAP));
        } else if(this.velocity.getY() < 0) {
           // if(!listener.pressedDown()){
                this.velocity.setY(Math.min(this.velocity.getY() + VERTICAL_SPEED_DECREASE , 0));
            //}
        }
        if(listener.pressedDown()){
            this.velocity.setY(Math.min(this.velocity.getY() + VERTICAL_SPEED_INCREASE, VERTICAL_SPEED_CAP));
        } else if(this.velocity.getY() > 0) {
           //if(!listener.pressedUp()){
                this.velocity.setY(Math.max(this.velocity.getY() - VERTICAL_SPEED_DECREASE, 0));
            //}
        }
        if(listener.pressedLeft()){
            this.velocity.setX(Math.max(this.velocity.getX() - HORIZONTAL_SPEED_INCREASE, -HORIZONTAL_SPEED_CAP));
        } else if(this.velocity.getX() < 0) {
           // if(!listener.pressedRight()){
                this.velocity.setX(Math.min(this.velocity.getX() + HORIZONTAL_SPEED_DECREASE, 0));
            //}
        }
        if(listener.pressedRight()){
            this.velocity.setX(Math.min(this.velocity.getX() + HORIZONTAL_SPEED_INCREASE, HORIZONTAL_SPEED_CAP));
        } else if(this.velocity.getX() > 0) {
          //  if(!listener.pressedLeft()){
                this.velocity.setX(Math.max(this.velocity.getX() - HORIZONTAL_SPEED_DECREASE, 0));
            //}
        }
    }
}
