package ro.uaic.info.engine.exception;

public class EngineExceptionUninitialized extends Exception{
    public EngineExceptionUninitialized(){
        super("Engine Uninitialised! Call initializeEngine() before run");
    }

    public EngineExceptionUninitialized(String message){
        super(message);
    }
}
