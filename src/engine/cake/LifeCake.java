package engine.cake;

import engine.Entity;
import sound.MSound;

import java.awt.*;
import java.awt.geom.Point2D;

public class LifeCake extends AbstractCake {
    public LifeCake(MSound sound) {
        super(sound);
    }

    public LifeCake(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size, sound);
    }


    @Override
    public void effect(Entity e) {
        int life = e.getLife();

        if (life < e.getMaxLife()) {
            e.setLife(life + 1);
        }

        playSound();
    }

    @Override
    public void playSound() {
        if (sound != null)
            sound.play();
    }
}
