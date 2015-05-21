package engine.effects;

import engine.AbstractMovableEntity;
import engine.Cooldown;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractBuff {

    private AbstractMovableEntity entity;
    private long timeSec;

    public AbstractBuff(AbstractMovableEntity entity, long time) {
        this.entity = entity;
        this.timeSec = time;
    }

    public void startBuff() {
        System.out.println("Apply.");
        apply();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("End.");
                end();
            }
        }, timeSec * 1000);
    }

    public abstract void apply();
    public abstract void end();

    public AbstractMovableEntity getAbstractMovableEntity() {
        return entity;
    }

    public void setAbstractMovableEntity(AbstractMovableEntity entity) {
        this.entity = entity;
    }

    public long getTime() {
        return timeSec;
    }
}
