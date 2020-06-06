package ro.uaic.info.game.objects.weapon.pattern.exception;

public class UndefinedProjectileType extends Exception {
    public UndefinedProjectileType(){
        super("Tried creating projectile without explicit type");
    }
}
