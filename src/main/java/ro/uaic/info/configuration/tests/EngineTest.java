package ro.uaic.info.configuration.tests;

import ro.uaic.info.engine.Debug;
import ro.uaic.info.engine.Engine;
import ro.uaic.info.engine.object.Trigger;
import ro.uaic.info.engine.object.transform.Transform;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.engine.sprite.AssetList;
import ro.uaic.info.engine.sprite.SpriteLoader;
import ro.uaic.info.game.objects.player.Player;
import ro.uaic.info.game.objects.ship.Ship;
import ro.uaic.info.engine.window.GameWindow;
import ro.uaic.info.game.objects.weapon.projectile.Projectile;

import java.awt.*;

public class EngineTest {
    public static int windowWidth = 1366;
    public static int windowHeight = 768;

    public static void main(String[] args) {

        Ship a = new Ship().setSprite(SpriteLoader.getInstance().getAsset(AssetList.PH_SHIP_1));
        a.getTransform().setLocation(new Double3(100, 100, 0));

        Engine.getInstance().initialiseEngine();

//        Engine.getInstance().getGameObjects().addGameObject(
//                new Player.PlayerBuilder().withShip(a).build()
//        );

//        Engine.getInstance().getGameObjects().addGameObject(Projectile.createIon(new Transform().setLocation(500, 500, 0)));
//        Engine.getInstance().getGameObjects().addGameObject(Projectile.createEnergy(new Transform().setLocation(500, 600, 0)));

        Engine.getInstance().getGameObjects().setPlayerObject(new Player.PlayerBuilder().withShip(a).build());
        Engine.getInstance().getGameObjects().addTrigger(
                new Trigger()
                    .setCollides(true)
                    .setTransform(
                            new Transform()
                                .setLocation(0,0,0)
                    )
                    .setLabel(Trigger.WORLD_EDGE)
                    .setMesh(
                            new Dimension(1366 - 15, 768 - 38)
                    )
        );

        new GameWindow
            .GameWindowBuilder()
                .withPosition(100,100)
                .withSize(1366, 768)
                .withDebugLevel(Debug.DebugLevel.DEBUG_LEVEL_CRITICAL)
                .drawMeshes(true)
                .build()
                .initialize()
                .run(false).pack();
    }
}
