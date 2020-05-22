package ro.uaic.info.configuration;

import ro.uaic.info.game.window.GameWindow;

public class EngineTest {
    public static void main(String[] args) {
        new GameWindow
            .GameWindowBuilder()
                .withPosition(100,100)
                .withSize(1366, 768)
                .initializeEngine()
                .build()
                .run(false);
    }
}
