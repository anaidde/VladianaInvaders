package ro.uaic.info.engine.entity;

import ro.uaic.info.engine.Engine;

public class FrameTimer {
    private int framesLeft;
    private int timerFrameCount;

    public FrameTimer(int frameCount){
        this.timerFrameCount = frameCount;
        Engine.getInstance().getGameObjects().addTimer(this);
    }

    public FrameTimer(){
        this.timerFrameCount = 0;
        Engine.getInstance().getGameObjects().addTimer(this);
    }

    public void start(){
        this.framesLeft = this.timerFrameCount;
    }

    public void start(int frameCount){
        this.framesLeft = this.timerFrameCount = frameCount;
    }

    public void tick(){
        if(this.framesLeft > 0)
            this.framesLeft--;
    }

    public boolean done(){
        return this.framesLeft == 0;
    }
}
