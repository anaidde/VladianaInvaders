package ro.uaic.info.game.entity;

import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.game.objects.player.Player;
import ro.uaic.info.game.objects.ship.Ship;
import ro.uaic.info.game.objects.weapon.Gun;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameObjects {
    private Player playerObject;

    private List<GameObject> gameObjects;

    private List<Gun> gunAssets;
    private List<Ship> shipAssets;

    public Player getPlayerObject() {
        return playerObject;
    }

    public GameObjects setPlayerObject(Player playerObject) {
        this.playerObject = playerObject;
        if(!this.gameObjects.contains(playerObject))
            this.gameObjects.add(playerObject);
        return this;
    }

    @Override
    public String toString() {
        return "GameObjects{" +
                "gameObjects=" + gameObjects +
                '}';
    }

    public GameObjects() {
        this.gameObjects = new ArrayList<>();

        this.gunAssets = new ArrayList<>();
        this.shipAssets = new ArrayList<>();
    }

    public void update() {
        this.gameObjects.forEach(GameObject::update);
    }

    public void redraw(Graphics g){
        this.gameObjects.forEach(o->o.draw(g));
    }

    public List<Gun> getGun() {
        return this.gunAssets;
    }

    public List<Ship> getShip() {
        return this.shipAssets;
    }

    public GameObjects addGameObject(GameObject gameObject){
        this.gameObjects.add(gameObject);
        return this;
    }

    public GameObjects addGameObjects(Collection<GameObject> gameObjects){
        this.gameObjects.addAll(gameObjects);
        return this;
    }
}
