package engine.effects;

import engine.AbstractMovableEntity;
import engine.Glutton;
import graphics.MainFrame;

public class InverseKeyBuff extends AbstractBuff {

    public InverseKeyBuff(AbstractMovableEntity entity, long time) {
        super(entity, time);
    }

    @Override
    public void apply() {
        AbstractMovableEntity entity = getAbstractMovableEntity();
        if (entity instanceof Glutton) {
            MainFrame.getCurrentInstance().getSettings().getKeyConfiguration().inverseMovements();
        }
    }

    @Override
    public void end() {
        AbstractMovableEntity entity = getAbstractMovableEntity();
        if (entity instanceof Glutton) {
            MainFrame.getCurrentInstance().getSettings().getKeyConfiguration().inverseMovements();
        }
    }
}
