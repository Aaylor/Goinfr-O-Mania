package engine.traps;

import engine.Cooldown;
import engine.Entity;
import engine.Valuable;
import sound.MSound;
import sound.Soundable;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class AbstractTrap extends Entity implements Soundable, Valuable {

    MSound sound;
    Cooldown damageCooldown;

    //CONSTRUCTORS


    public AbstractTrap(MSound sound) {
        super();
        this.sound = sound;
        damageCooldown = new Cooldown(1);
    }

    public AbstractTrap(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size);
        this.sound = sound;
        damageCooldown = new Cooldown(1);
    }

    public AbstractTrap(Point2D position, Dimension size, boolean crossable, MSound sound) {
        super(position, size, crossable);
        this.sound = sound;
        damageCooldown = new Cooldown(1);
    }

    public AbstractTrap(Point2D position, Dimension size, boolean crossable,
                        MSound sound, long cooldown) {
        super(position, size, crossable);
        this.sound = sound;
        this.damageCooldown = new Cooldown(cooldown);
    }

    public Cooldown getCooldown() {
        return damageCooldown;
    }

    @Override
    public void playSound() {
        if (sound != null)
            sound.play();
    }
}
