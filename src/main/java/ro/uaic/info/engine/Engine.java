package ro.uaic.info.engine;

import ro.uaic.info.engine.exception.EngineExceptionNoWindow;
import ro.uaic.info.engine.exception.EngineExceptionUninitialized;
import ro.uaic.info.game.entity.GameObjects;
import ro.uaic.info.game.window.GameWindow;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Engine {
    private static Engine instance;

    /**
     * debugLevel 1 = critic only
     * debugLevel 2 = important + critical
     * debugLevel 3 = anything.
     */
    private int debugLevel = 0;

    private boolean isActive = false;

    public static int DEFAULT_FPS_TARGET = 60;

    public static final int DEBUG_LEVEL_NONE = 0;
    public static final int DEBUG_LEVEL_CRITICAL = 1;
    public static final int DEBUG_LEVEL_IMPORTANT = 2;
    public static final int DEBUG_LEVEL_ALL_MESSAGES = 20;

    private int targetFPS = DEFAULT_FPS_TARGET;

    private GameObjects gameObjects;
    private GameWindow window;

    private boolean initialized = false;

    public Engine setDebugLevel(int debugLevel){
        this.debugLevel = debugLevel;
        return this;
    }

    public static Engine getInstance(){
        if(Engine.instance == null)
            Engine.instance = new Engine();
        return Engine.instance;
    }

    private Engine(){
    }

    public GameObjects getGameObjects() {
        return this.gameObjects;
    }

    /**
     * Call to initialise engine components. Call if initAtRuntime = false
     * @return
     */
    public Engine initialiseEngine(){
        if(this.initialized)
            return this;

        if(this.isActive)
            return this;

        this.gameObjects = new GameObjects();

        this.initialized = true;
        return this;
    }

    /**
     * Will call ALL objects in engine that need updating every frame
     */
    private void update(){
        /*
            TODO : Update ALL game rules here (objects etc. )
         */

        this.redraw();
    }

    private void redraw(){
        BufferStrategy strategy = this.window.getCanvas().getBufferStrategy();

        if(strategy == null){
            this.window.getCanvas().createBufferStrategy(3);
            strategy = this.window.getCanvas().getBufferStrategy();
        }

        Graphics graphics = strategy.getDrawGraphics();
        graphics.clearRect(0,0, this.window.getWidth(), this.window.getHeight());

        /*
         *  TODO : REDRAW ALL OBJECTS HERE
         */

        strategy.show();
        graphics.dispose();
    }

    public void stopEngine(){
        this.isActive = false;
    }

    public Engine setGameWindow(GameWindow window) {
        this.window = window;
        return this;
    }

    /**
     * Call after initialized or toggle initAtRuntime, more overhead if so.
     * @param initAtRuntime true to init at runtime, false to init before
     * @throws EngineExceptionUninitialized if uninitialized, will throw
     */
    public synchronized void run(boolean initAtRuntime) throws EngineExceptionUninitialized, EngineExceptionNoWindow {
        if(initAtRuntime)
            this.initialiseEngine();

        if(!initialized)
            throw new EngineExceptionUninitialized();

        if(this.window == null)
            throw new EngineExceptionNoWindow();

        this.isActive = true;

        long startFrameTime = 0;
        long endFrameTime = 0;

        int updateTime;
        int sleepTimer = (1000 / this.targetFPS); // 1000ms / frames per Second = one frame's duration

        while(this.isActive){
            startFrameTime = System.nanoTime();

            this.update();

            endFrameTime = System.nanoTime();

            updateTime = (int) ((endFrameTime - startFrameTime) / 1000000);

            try{
                if(sleepTimer >= updateTime)
                    Thread.sleep(sleepTimer - updateTime);
            } catch (InterruptedException ignored){

            }
        }
    }

}
