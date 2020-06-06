package ro.uaic.info.game.objects.weapon;

import ro.uaic.info.engine.Debug;
import ro.uaic.info.engine.Engine;
import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.game.objects.weapon.exception.GunNoObjectAttached;
import ro.uaic.info.game.objects.weapon.pattern.FiringPattern;
import ro.uaic.info.game.objects.weapon.pattern.IonPattern;
import ro.uaic.info.game.objects.weapon.pattern.exception.UndefinedProjectileType;
import ro.uaic.info.game.objects.weapon.projectile.Projectile;
import ro.uaic.info.game.objects.weapon.projectile.ProjectileType;

import java.awt.event.KeyListener;

public class Gun {
    private ProjectileType projectileType;
    private FiringPattern firingPattern;
    private GameObject shooter = null;
    private int level = 1;

    public static class GunBuilder{
        private FiringPattern firingPattern;
        private GameObject attachedObject;
        private ProjectileType projectileType;

        public GunBuilder withPattern(FiringPattern firingPattern){
            this.firingPattern = firingPattern;
            return this;
        }

        public GunBuilder withShooter(GameObject shooter){
            this.attachedObject = shooter;
            return this;
        }

        public GunBuilder withProjectileType(ProjectileType type){
            this.projectileType = type;
            return this;
        }

        public Gun build(){
            Gun result = new Gun();

            result.setShooter(attachedObject);
            try {
                firingPattern.setShooter(result);
            } catch (GunNoObjectAttached gunNoObjectAttached) {
                gunNoObjectAttached.printStackTrace();
            }

            result.setFiringPattern(firingPattern);
            result.setLevel(1);

            return result;

        }
    }

    public Gun(ProjectileType projectileType, FiringPattern firingPattern) {
        this.projectileType = projectileType;
        this.firingPattern = firingPattern;
    }

    public Gun(FiringPattern firingPattern){
        this.firingPattern = firingPattern;
    }

    public Gun(){

    }

    public GameObject getShooter() {
        return shooter;
    }

    public void setFiringPattern(FiringPattern firingPattern) {
        this.firingPattern = firingPattern;
    }

    public Gun setShooter(GameObject o){
        this.shooter = o;
        return this;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void levelUpWeapon(){
        this.level++;
    }

    public Transform getTransform() throws GunNoObjectAttached {
        if(this.shooter == null)
            throw new GunNoObjectAttached();
        return shooter.getTransform();
    }

    public void fire(){
        try {
            //Engine.getInstance().debug(this.firingPattern.fireNextProjectileSet(this.level).toString(), Debug.DebugLevel.DEBUG_LEVEL_ALL_MESSAGES);
            Engine.getInstance().getGameObjects().addGameObjects(this.firingPattern.fireNextProjectileSet(this.level));
        } catch (UndefinedProjectileType undefinedProjectileType) {
            undefinedProjectileType.printStackTrace();
        }
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public FiringPattern getFiringPattern() {
        return firingPattern;
    }
}
