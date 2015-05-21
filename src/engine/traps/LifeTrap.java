package engine.traps;

import engine.Entity;
import engine.weapons.Attackable;
import log.IGLog;
import sound.MSound;

import java.awt.*;
import java.awt.geom.Point2D;

public class LifeTrap extends AbstractTrap {

    private int damage;
    private boolean hasScored = false;

    public LifeTrap(MSound sound, int damage) {
        super(sound);
        this.damage = damage;
    }

    public LifeTrap(Point2D startPosition, Dimension size, MSound sound, int damage) {
        super(startPosition, size, sound);
        this.damage = damage;
    }

    public LifeTrap(Point2D position, Dimension size, boolean crossable,
                    MSound sound, int damage) {
        super(position, size, crossable, sound);
        this.damage = damage;
    }

    public LifeTrap(Point2D position, Dimension size, boolean crossable,
                    MSound sound, long cooldown, int damage) {
        super(position, size, crossable, sound, cooldown);
        this.damage = damage;
    }

    @Override
    public void effect(Entity e) {
        if (e instanceof Attackable && getCooldown().isReady()) {
            playSound();
            getCooldown().start();
            hasScored = false;

            IGLog.write("Trap::effect -> effect on " + e);
                    ((Attackable) e).takeDamage(damage);
        }
    }

    @Override
    public int scoreValue() {
        if (!hasScored) {
            hasScored = true;
            return -5;
        }

        return 0;
    }
}
