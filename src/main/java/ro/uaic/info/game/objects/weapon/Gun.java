package ro.uaic.info.game.objects.weapon;

import ro.uaic.info.game.objects.weapon.projectile.ProjectileType;

public class Gun {
    private ProjectileType projectileType;
    private FiringPattern firingPattern;

    public Gun(ProjectileType projectileType, FiringPattern firingPattern) {
        this.projectileType = projectileType;
        this.firingPattern = firingPattern;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public FiringPattern getFiringPattern() {
        return firingPattern;
    }
}
