package engine.cake;

import engine.Entity;
import engine.Valuable;
import sound.MSound;
import sound.Soundable;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class AbstractCake extends Entity implements Soundable, Valuable {

    MSound sound;

    //CONSTRUCTORS

    public AbstractCake(MSound sound) {
        this.sound = sound;
    }

    public AbstractCake(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size);
        this.sound = sound;
    }

}
