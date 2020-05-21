package ro.uaic.info.engine;

import ro.uaic.info.engine.exception.EngineExceptionUninitialized;

public class Engine {
    private static Engine instance;

    /**
     * debugLevel 1 = critic only
     * debugLevel 2 = important + critic
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

    private Engine initialiseEngine(){

        this.initialized = true;
        return this;
    }

    /**
     * Will call ALL objects in engine that need updating every frame
     * @return
     */
    private void update(){

    }

    public synchronized void run(boolean initAtRuntime) throws EngineExceptionUninitialized {
        if(initAtRuntime)
            this.initialiseEngine();

        if(!initialized)
            throw new EngineExceptionUninitialized();

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
