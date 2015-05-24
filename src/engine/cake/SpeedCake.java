package engine.cake;

import engine.AbstractMovableEntity;
import engine.Entity;
import engine.effects.IncreaseSpeedBuff;
import sound.MSound;

import java.awt.*;
import java.awt.geom.Point2D;

public class SpeedCake extends AbstractCake {

    public SpeedCake(MSound sound) {
        super(sound);
    }

    public SpeedCake(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size, sound);
    }

    @Override
    public boolean effect(Entity e) {
        if (e instanceof AbstractMovableEntity) {
            new IncreaseSpeedBuff((AbstractMovableEntity) e, 5).startBuff();
            playSound();
            return true;
        }

        return false;
    }

    @Override
    public int scoreValue() {
        return 20;
    }

}
