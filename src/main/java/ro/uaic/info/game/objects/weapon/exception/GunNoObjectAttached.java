package ro.uaic.info.game.objects.weapon.exception;

public class GunNoObjectAttached extends Exception {
    public GunNoObjectAttached(){
        super("No GameObject attached to this gun object");
    }
}
