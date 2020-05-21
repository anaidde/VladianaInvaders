package ro.uaic.info.engine;

public class Engine {
    private static Engine instance;

    /**
     * debugLevel 1 = critic only
     * debugLevel 2 = important + critic
     * debugLevel 3 = anything.
     */
    private int debugLevel = 0;

    public static final int DEBUG_LEVEL_NONE = 0;
    public static final int DEBUG_LEVEL_CRITICAL = 1;
    public static final int DEBUG_LEVEL_IMPORTANT = 2;
    public static final int DEBUG_LEVEL_ALL_MESSAGES = 20;

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

    private void initialiseEngine(){

    }

    private void update(){

    }

    public void run(){

    }

}
