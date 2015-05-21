package engine.effects;

import engine.AbstractMovableEntity;
import engine.Entity;

public class SlowSpeedBuff extends AbstractBuff {

    private final float SPEED_MODIFIER = 0.7f;

    private float initialSpeed;

    public SlowSpeedBuff(AbstractMovableEntity entity, long time) {
        super(entity, time);
    }

    @Override
    public void apply() {
        AbstractMovableEntity ame = getAbstractMovableEntity();
        initialSpeed = ame.getSpeed();
        ame.setSpeed(initialSpeed * SPEED_MODIFIER);
    }

    @Override
    public void end() {
        getAbstractMovableEntity().setSpeed(initialSpeed);
    }
}
