package helpers;

import java.util.Date;

public class PopTimer {

    private Date startTime;
    private Date dueTime;
    private Date pauseTime;

    public PopTimer(long second) {
        startTime = new Date();
        dueTime   = new Date(startTime.getTime() + (second * 1000));
        pauseTime = null;
    }

    public void pauseTimer() {
        pauseTime = new Date();
    }

    public void resumeTimer() {
        if (hasPassed()) return;

        if (pauseTime != null) {
            long rest = dueTime.getTime() - pauseTime.getTime();
            dueTime = new Date(new Date().getTime() + rest);
            pauseTime = null;
        }
    }

    public boolean hasPassed() {
        return ExtDate.hasPassed(dueTime);
    }

}
