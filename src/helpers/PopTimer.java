package helpers;

public class PopTimer {

    private long startTime;
    private long dueTime;
    private long pauseTime;

    public PopTimer(long second) {
        startTime = System.currentTimeMillis();
        dueTime   = startTime + (second * 1000);
        pauseTime = 0;
    }

    public void pauseTimer() {
        pauseTime = System.currentTimeMillis();
    }

    public void resumeTimer() {
        if (pauseTime == 0)
            return;

        /* add the waiting time to the due time. */
        dueTime += (System.currentTimeMillis() - pauseTime);
        pauseTime = 0;
    }

    public boolean hasPassed() {
        return System.currentTimeMillis() >= dueTime;
    }

}
