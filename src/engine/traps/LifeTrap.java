package engine.traps;

import engine.Entity;
import engine.weapons.Attackable;
import log.IGLog;
import sound.MSound;

import java.awt.*;
import java.awt.geom.Point2D;

public class LifeTrap extends AbstractTrap {

    private int damage;

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
    void applyEffect(Entity e) {
        if (e instanceof Attackable) {
            IGLog.write("Trap::effect -> effect on " + e);
            ((Attackable) e).takeDamage(damage);
        }
    }

    @Override
    int getScoreValue() {
        return -5;
    }

}
