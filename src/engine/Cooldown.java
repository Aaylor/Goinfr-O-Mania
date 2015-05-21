package engine;

import java.util.Date;

public class Cooldown {

    private long time;
    private boolean cooldown;


    public Cooldown(long time) {
        this.time = time;
        cooldown = true;
    }

    public long getTime() {
        return time;
    }

    public void start() {
        if (cooldown) {
            new Thread() {
                @Override
                public void run() {
                    cooldown = false;
                    Date wanted = new Date(new Date().getTime() + time);
                    do {
                        try {
                            Thread.sleep(time);
                            break;
                        } catch (InterruptedException e) {
                            if (new Date().after(wanted))
                                break;
                        }
                    } while (true);
                    cooldown = true;
                }
            }.start();
        }
    }

    public boolean isReady() {
        return cooldown;
    }

}
