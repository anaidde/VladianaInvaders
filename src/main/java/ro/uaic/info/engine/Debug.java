package ro.uaic.info.engine;

public class Debug {
    public enum DebugLevel{
        DEBUG_LEVEL_NONE,
        DEBUG_LEVEL_CRITICAL,
        DEBUG_LEVEL_IMPORTANT,
        DEBUG_LEVEL_ALL_MESSAGES,
    }

    /**
     * debugLevel 1 = critic only
     * debugLevel 2 = important + critical
     * debugLevel 3 = anything.
     */
    private DebugLevel debugLevel;

    public Debug(){
        this.debugLevel = DebugLevel.DEBUG_LEVEL_NONE;
    }

    public void setDebugLevel(DebugLevel level){
        this.debugLevel = level;
    }

    public void printMessage(String message, DebugLevel messageLevel, boolean forcePrint, String context){
        if(forcePrint || messageLevel.ordinal() <= this.debugLevel.ordinal())
            System.out.println("[" + context + " DEBUG] " + message + " ,level = " + messageLevel.toString());
    }
}
