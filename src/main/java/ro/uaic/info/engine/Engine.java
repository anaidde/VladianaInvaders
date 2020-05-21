package ro.uaic.info.engine;

public class Engine {
    private static Engine instance;

    public static Engine getInstance(){
        if(Engine.instance == null)
            Engine.instance = new Engine();
        return Engine.instance;
    }

    private Engine(){

    }
}
