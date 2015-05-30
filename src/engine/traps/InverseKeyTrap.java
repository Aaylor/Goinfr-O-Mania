package engine.traps;

import engine.AbstractMovableEntity;
import engine.Entity;
import engine.effects.InverseKeyBuff;
import sound.MSound;

import java.awt.*;
import java.awt.geom.Point2D;

public class InverseKeyTrap extends AbstractTrap {
    public InverseKeyTrap(MSound sound) {
        super(sound);
    }

    public InverseKeyTrap(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size, sound);
    }

    public InverseKeyTrap(Point2D position, Dimension size, boolean crossable, MSound sound) {
        super(position, size, crossable, sound);
    }

    public InverseKeyTrap(Point2D position, Dimension size, boolean crossable, MSound sound, long cooldown, long lifetime) {
        super(position, size, crossable, sound, cooldown, lifetime);
    }

    @Override
    boolean applyEffect(Entity e) {
        if (e instanceof AbstractMovableEntity) {
            new InverseKeyBuff((AbstractMovableEntity)e, 5).startBuff();
            return true;
        }

        return false;
    }

    @Override
    int getScoreValue() {
        return -10;
    }
}
