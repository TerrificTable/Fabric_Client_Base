package xyz.terrific.mod.utils;

public class Timer {

    public long elapsed;
    public long started;


    public Timer() {
        this.elapsed = 0;
        this.started = System.currentTimeMillis();
    }


    public void update() {
        this.elapsed = System.currentTimeMillis() - this.started;
    }

    public long getStarted() {
        return this.started;
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public long getElapsed() {
        update();
        return this.elapsed;
    }

    public void reset() {
        this.elapsed = 0;
        this.started = System.currentTimeMillis();
    }

}
