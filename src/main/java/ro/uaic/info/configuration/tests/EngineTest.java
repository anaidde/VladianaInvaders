package ro.uaic.info.configuration.tests;

import ro.uaic.info.engine.Debug;
import ro.uaic.info.game.window.GameWindow;

public class EngineTest {
    public static void main(String[] args) {
        new GameWindow
            .GameWindowBuilder()
                .withPosition(100,100)
                .withSize(1366, 768)
                .withDebugLevel(Debug.DebugLevel.DEBUG_LEVEL_ALL_MESSAGES)
                .build()
                .initialize()
                .run(false);
    }
}
