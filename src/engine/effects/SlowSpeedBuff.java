package engine.effects;

import engine.AbstractMovableEntity;
import engine.Entity;

public class SlowSpeedBuff extends AbstractBuff {

    private static int SPEED_MODIFIER = -30;

    public SlowSpeedBuff(AbstractMovableEntity entity, long time) {
        super(entity, time);
    }

    @Override
    public void apply() {
        getAbstractMovableEntity().addSpeedModifier(SPEED_MODIFIER);
    }

    @Override
    public void end() {
        getAbstractMovableEntity().addSpeedModifier(-SPEED_MODIFIER);
    }
}
