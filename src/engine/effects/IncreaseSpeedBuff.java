package engine.effects;

import engine.AbstractMovableEntity;

public class IncreaseSpeedBuff extends AbstractBuff {

    private static float SPEED_CHANGE = 1.5f;

    private float speed;

    public IncreaseSpeedBuff(AbstractMovableEntity entity, long time) {
        super(entity, time);
    }

    @Override
    public void apply() {
        speed = getAbstractMovableEntity().getSpeed();
        getAbstractMovableEntity().setSpeed(SPEED_CHANGE * speed);
    }

    @Override
    public void end() {
        getAbstractMovableEntity().setSpeed(speed);
    }

}
