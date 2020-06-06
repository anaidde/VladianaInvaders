package ro.uaic.info.game.objects.weapon.pattern;

import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.game.objects.weapon.Gun;
import ro.uaic.info.game.objects.weapon.exception.GunNoObjectAttached;
import ro.uaic.info.game.objects.weapon.pattern.exception.UndefinedProjectileType;
import ro.uaic.info.game.objects.weapon.projectile.Projectile;
import ro.uaic.info.game.objects.weapon.projectile.ProjectileType;

import java.util.List;

public interface FiringPattern {

//    public Projectile fireNextProjectile() {
//        return (new Projectile());
//    }

    static Projectile getProjectileByType(ProjectileType type, Transform where) throws UndefinedProjectileType {
        switch (type){
            case CLASSIC_RED:   return Projectile.createIon(where);
            case ENERGY_GREEN:  return Projectile.createEnergy(where);
            default: throw new UndefinedProjectileType();
        }
    }

    Transform getTransform();
    Projectile getProjectile() throws UndefinedProjectileType;
    List<GameObject> fireNextProjectileSet(int weaponLevel) throws UndefinedProjectileType;
    void setProjectileType(ProjectileType type);
    void setShooter(Gun shooter) throws GunNoObjectAttached;
}
