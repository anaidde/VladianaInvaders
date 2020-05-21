package ro.uaic.info.game.entity;

import ro.uaic.info.game.objects.player.Player;
import ro.uaic.info.game.objects.ship.Ship;
import ro.uaic.info.game.objects.weapon.Gun;

import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    private Player playerObject;
    private List<Gun> gunAssets;
    private List<Ship> shipAssets;

    public GameObjects() {
        this.gunAssets = new ArrayList<>();
        this.shipAssets = new ArrayList<>();
    }

    public void update() {

    }

    public Player getPlayer() {
        return this.playerObject;
    }

    public List<Gun> getGun() {
        return this.gunAssets;
    }

    public List<Ship> getShip() {
        return this.shipAssets;
    }
}
