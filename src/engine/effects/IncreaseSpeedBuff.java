package engine.effects;

import engine.AbstractMovableEntity;

public class IncreaseSpeedBuff extends AbstractBuff {

    private static int SPEED_MODIFIER = 50;

    public IncreaseSpeedBuff(AbstractMovableEntity entity, long time) {
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
