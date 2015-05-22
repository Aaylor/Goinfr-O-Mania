package engine.cake;

import engine.Entity;
import engine.Glutton;
import engine.nutritionists.CakeChaserNutritionist;
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
    public boolean effect(Entity e) {
        if (e instanceof Glutton || e instanceof CakeChaserNutritionist) {
            int life = e.getLife();
            if (life + lifeGiven > e.getMaxLife()) {
                e.setLife(e.getMaxLife());
            } else {
                e.setLife(life + lifeGiven);
            }

            playSound();
            return true;
        }

        return false;
    }

    @Override
    public int scoreValue() {
        return 10;
    }
}
