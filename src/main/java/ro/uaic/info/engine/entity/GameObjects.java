package ro.uaic.info.engine.entity;

import ro.uaic.info.engine.object.GameObject;
import ro.uaic.info.engine.object.Trigger;
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
    private List<Trigger> triggers;

    private List<GameObject> toBeAddedAfterFrame;

    private List<FrameTimer> coolDownTimers;

    private List<Gun> gunAssets;
    private List<Ship> shipAssets;

    public List<GameObject> getAll() {
        return gameObjects;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }

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
        this.triggers = new ArrayList<>();

        this.coolDownTimers = new ArrayList<>();

        this.gunAssets = new ArrayList<>();
        this.shipAssets = new ArrayList<>();

        this.toBeAddedAfterFrame = new ArrayList<>();
    }

    public void update() {
        this.gameObjects.forEach(GameObject::realUpdate);
        this.coolDownTimers.forEach(FrameTimer::tick);

        this.gameObjects.removeIf(GameObject::toBeDestroyed);

        this.gameObjects.addAll(this.toBeAddedAfterFrame);
        this.toBeAddedAfterFrame.clear();

        System.out.println(this.gameObjects.toString());
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

    public GameObjects addTimer(FrameTimer timer){
        this.coolDownTimers.add(timer);
        return this;
    }

    public GameObjects addGameObject(GameObject gameObject){
        this.toBeAddedAfterFrame.add(gameObject);
        return this;
    }

    public GameObjects addGameObjects(Collection<GameObject> gameObjects){
        this.toBeAddedAfterFrame.addAll(gameObjects);
        return this;
    }

    public GameObjects addTrigger(Trigger trigger){
        if(!this.triggers.contains(trigger))
            this.triggers.add(trigger);
        return this;
    }
}
