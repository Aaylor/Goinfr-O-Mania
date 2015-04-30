package engine;

import sound.MSound;
import sound.Soundable;

import java.awt.*;
import java.awt.geom.Point2D;

public class Cake extends Entity implements Soundable {

    MSound sound;

    //CONSTRUCTORS

    public Cake(MSound sound) {
        this.sound = sound;
    }

    public Cake(Point2D startPosition, Dimension size, MSound sound) {
        super(startPosition, size);
        this.sound = sound;
    }

    @Override
    public void playSound() {

    }

    @Override
    public void effect(Entity e) {

    }
}
