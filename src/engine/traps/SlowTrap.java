package engine.traps;

import engine.AbstractMovableEntity;
import engine.Entity;
import engine.effects.SlowSpeedBuff;
import sound.MSound;

import java.awt.*;
import java.awt.geom.Point2D;

public class SlowTrap extends AbstractTrap {
    public SlowTrap(MSound sound) {
        super(sound);
    }

    public SlowTrap(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size, sound);
    }

    public SlowTrap(Point2D position, Dimension size, boolean crossable, MSound sound) {
        super(position, size, crossable, sound);
    }

    public SlowTrap(Point2D position, Dimension size, boolean crossable, MSound sound, long cooldown) {
        super(position, size, crossable, sound, cooldown);
    }

    @Override
    void applyEffect(Entity e) {
        if (e instanceof AbstractMovableEntity)
            new SlowSpeedBuff((AbstractMovableEntity) e, 3).startBuff();
    }

    @Override
    int getScoreValue() {
        return -2;
    }

}
