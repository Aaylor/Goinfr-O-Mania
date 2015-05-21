package engine.cake;

import engine.Entity;
import sound.MSound;

import java.awt.*;
import java.awt.geom.Point2D;

public class LifeCake extends AbstractCake {

    int lifeGiven;

    public LifeCake(MSound sound) {
        super(sound);
        lifeGiven = 1;
    }

    public LifeCake(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size, sound);
        lifeGiven = 1;
    }

    public LifeCake(Point2D startPosition, Dimension size, MSound sound, int lifeGiven) {
        super(startPosition, size, sound);
        this.lifeGiven = lifeGiven;
    }

    @Override
    public void effect(Entity e) {
        int life = e.getLife();

        if (life + lifeGiven > e.getMaxLife()) {
            e.setLife(e.getMaxLife());
        } else {
            e.setLife(life + lifeGiven);
        }

        playSound();
    }

    @Override
    public int scoreValue() {
        return 10;
    }
}
