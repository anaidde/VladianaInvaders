package ro.uaic.info.game.objects.weapon;

import ro.uaic.info.game.objects.weapon.projectile.Projectile;

public class FiringPattern {

    public Projectile fireNextProjectile() {
        return (new Projectile());
    }
}
