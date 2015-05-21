package engine.traps;

import engine.Cooldown;
import engine.Entity;
import engine.Valuable;
import engine.weapons.Attackable;
import log.IGLog;
import sound.MSound;
import sound.Soundable;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class AbstractTrap extends Entity implements Soundable, Valuable {

    private MSound sound;
    private Cooldown damageCooldown;

    private boolean hasScored = false;

    //CONSTRUCTORS


    public AbstractTrap(MSound sound) {
        super();
        this.sound = sound;
        damageCooldown = new Cooldown(1000);
    }

    public AbstractTrap(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size);
        this.sound = sound;
        damageCooldown = new Cooldown(1000);
    }

    public AbstractTrap(Point2D position, Dimension size, boolean crossable, MSound sound) {
        super(position, size, crossable);
        this.sound = sound;
        damageCooldown = new Cooldown(1000);
    }

    public AbstractTrap(Point2D position, Dimension size, boolean crossable,
                        MSound sound, long cooldown) {
        super(position, size, crossable);
        this.sound = sound;
        this.damageCooldown = new Cooldown(cooldown);
    }

    abstract void applyEffect(Entity e);
    abstract int getScoreValue();

    public Cooldown getCooldown() {
        return damageCooldown;
    }

    @Override
    public final void playSound() {
        if (sound != null)
            sound.play();
    }

    @Override
    public final void effect(Entity e) {
        if (getCooldown().isReady()) {
            playSound();
            getCooldown().start();
            hasScored = false;

            applyEffect(e);
        }
    }

    @Override
    public final int scoreValue() {
        if (!hasScored) {
            hasScored = true;
            return getScoreValue();
        }

        return 0;
    }
}
