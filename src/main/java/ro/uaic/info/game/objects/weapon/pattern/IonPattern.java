package ro.uaic.info.game.objects.weapon.pattern;

import ro.uaic.info.engine.entity.FrameTimer;
import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.game.objects.ship.Ship;
import ro.uaic.info.game.objects.weapon.Gun;
import ro.uaic.info.game.objects.weapon.exception.GunNoObjectAttached;
import ro.uaic.info.game.objects.weapon.pattern.exception.UndefinedProjectileType;
import ro.uaic.info.game.objects.weapon.projectile.IonProjectile;
import ro.uaic.info.game.objects.weapon.projectile.Projectile;
import ro.uaic.info.game.objects.weapon.projectile.ProjectileType;

import java.util.ArrayList;
import java.util.List;

public class IonPattern implements FiringPattern {

    public static final int PATTERN_FIRE_COOL_DOWN_FRAMES = 15;

    private FrameTimer coolDownTimer = new FrameTimer(PATTERN_FIRE_COOL_DOWN_FRAMES);

    private Transform projectileStartTransform = new Transform();
    private ProjectileType projectileType = ProjectileType.UNDEFINED;

    private Gun shooter;

    public IonPattern(Gun shooter) throws GunNoObjectAttached {
        this.shooter = shooter;
        this.projectileStartTransform = shooter.getTransform();
    }

    public IonPattern(){

    }

    public void setShooter(Gun shooter) throws GunNoObjectAttached {
        this.shooter = shooter;
        this.projectileStartTransform = shooter.getTransform();
    }

    @Override
    public Transform getTransform() {
        return projectileStartTransform;
    }

    @Override
    public Projectile getProjectile() throws UndefinedProjectileType {
        if(! projectileType.equals(ProjectileType.UNDEFINED) )
            return FiringPattern.getProjectileByType( projectileType, this.getTransform() );

        Transform accurateStartTransform = this.getTransform().copy();
        accurateStartTransform.setLocation(
                accurateStartTransform.getLocation().getX() + this.shooter.getShooter().getWidth() / 2 - (double)IonProjectile.MESH_WIDTH / 2,
                accurateStartTransform.getLocation().getY() - IonProjectile.MESH_HEIGHT, ///+ this.shooter.getShooter().getTransform().getVelocity().getY() * 2,
                0
        );

        return Projectile.createIon(accurateStartTransform);
    }

    @Override
    public List<GameObject> fireNextProjectileSet(int weaponLevel) throws UndefinedProjectileType {
        List<GameObject> projectiles = new ArrayList<>();

        if(this.coolDownTimer.done()) {
            if (weaponLevel == 1) {
                projectiles.add(getProjectile());
            } /// else if weapon levels 1 through 20? 10?

            this.coolDownTimer.start();
        }

        return projectiles;
    }

    @Override
    public void setProjectileType(ProjectileType type) {
        this.projectileType = type;
    }
}
