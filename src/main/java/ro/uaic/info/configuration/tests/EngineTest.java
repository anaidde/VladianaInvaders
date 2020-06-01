package ro.uaic.info.configuration.tests;

import ro.uaic.info.engine.Debug;
import ro.uaic.info.engine.Engine;
import ro.uaic.info.engine.space.Double3;
import ro.uaic.info.engine.sprite.AssetList;
import ro.uaic.info.engine.sprite.SpriteLoader;
import ro.uaic.info.game.objects.player.Player;
import ro.uaic.info.game.objects.ship.Ship;
import ro.uaic.info.game.window.GameWindow;

public class EngineTest {
    public static void main(String[] args) {

        Ship a = new Ship().setSprite(SpriteLoader.getInstance().getAsset(AssetList.PH_SHIP_1));
        a.getTransform().setLocation(new Double3(100, 100, 0));

        Engine.getInstance().initialiseEngine();

//        Engine.getInstance().getGameObjects().addGameObject(
//                new Player.PlayerBuilder().withShip(a).build()
//        );

        Engine.getInstance().getGameObjects().setPlayerObject(new Player.PlayerBuilder().withShip(a).build());

        new GameWindow
            .GameWindowBuilder()
                .withPosition(100,100)
                .withSize(1366, 768)
                .withDebugLevel(Debug.DebugLevel.DEBUG_LEVEL_CRITICAL)
                .build()
                .initialize()
                .run(false);

    }
}
