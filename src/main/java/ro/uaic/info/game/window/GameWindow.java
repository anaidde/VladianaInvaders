package ro.uaic.info.game.window;

import ro.uaic.info.engine.Debug;
import ro.uaic.info.engine.Engine;
import ro.uaic.info.engine.exception.EngineExceptionNoWindow;
import ro.uaic.info.engine.exception.EngineExceptionUninitialized;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Engine engine;

    public static int DEFAULT_X_OFFSET = 0;
    public static int DEFAULT_Y_OFFSET = 0;
    public static int DEFAULT_GAME_WINDOW_WIDTH = 1366;
    public static int DEFAULT_GAME_WINDOW_HEIGHT = 1366;

    private Point topLeftCorner;
    private Dimension windowSize;
    private Canvas canvas;

    public Canvas getCanvas() {
        return this.canvas;
    }

    public static class GameWindowBuilder{
        private Point topLeftCorner = new Point(DEFAULT_X_OFFSET,DEFAULT_Y_OFFSET);
        private Dimension gameWindowDimension = new Dimension(DEFAULT_GAME_WINDOW_WIDTH, DEFAULT_GAME_WINDOW_HEIGHT);
        private boolean initEng = false;

        public GameWindowBuilder withPosition(Point position){
            this.topLeftCorner = position;
            return this;
        }

        public GameWindowBuilder withSize(Dimension dimension){
            this.gameWindowDimension = dimension;
            return this;
        }

        public GameWindowBuilder withPosition(int x, int y){
            this.topLeftCorner = new Point(x, y);
            return this;
        }

        public GameWindowBuilder withSize(int width, int height){
            this.gameWindowDimension = new Dimension(width, height);
            return this;
        }

        public GameWindowBuilder initializeEngine(){
            this.initEng = true;
            return this;
        }

        public GameWindow build() {
            GameWindow gameWindow = new GameWindow();
            gameWindow.topLeftCorner = this.topLeftCorner;
            gameWindow.windowSize = this.gameWindowDimension;

            gameWindow.engine = Engine
                    .getInstance()
                    .setDebugLevel(Debug.DebugLevel.DEBUG_LEVEL_NONE);
            if(this.initEng)
                gameWindow.engine.initialiseEngine();

            return gameWindow;
        }
    }

    public GameWindow initialize(){
        this.engine.initialiseEngine(); /// will return if already initialized

        return this.buildWindow().buildComponents();
    }

    private GameWindow buildWindow(){
        this.setLocation(this.topLeftCorner);
        this.setSize(this.windowSize);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        return this;
    }

    private GameWindow buildComponents(){
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.canvas.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));
        this.canvas.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
        this.canvas.setFocusable(false);

        this.canvas.createBufferStrategy(3); ///Triple Buffering : 3 imagini sunt pre-loaded in GPU pt smooth rendering si swap intre imagini

        return this;
    }

    public GameWindow run(boolean init){

        if(init)
            this.initialize();

        try {
            this.engine.setGameWindow(this).run(false);
        } catch ( EngineExceptionUninitialized exceptionUninitialized ){
            System.out.println(exceptionUninitialized + ". Init Engine!");
        } catch ( EngineExceptionNoWindow exceptionNoWindow ){
            System.out.println(exceptionNoWindow.toString());
        }

        return this;
    }

    private GameWindow(){

    }
}
