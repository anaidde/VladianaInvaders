package ro.uaic.info.game.entity;

import ro.uaic.info.game.objects.player.Player;
import ro.uaic.info.game.objects.ship.Ship;
import ro.uaic.info.game.objects.weapon.Gun;

public class GameObjects {
    private Player player;
    private Gun gun;
    private Ship ship;

    public GameObjects(Player player, Gun gun, Ship ship) {
        this.player = player;
        this.gun = gun;
        this.ship = ship;
    }

    public void update() {

    }

    public Player getPlayer() {
        return player;
    }

    public Gun getGun() {
        return gun;
    }

    public Ship getShip() {
        return ship;
    }
}
