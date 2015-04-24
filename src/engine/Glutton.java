package engine;

import java.awt.*;

/**
 * Created by tratost on 13/04/15.
 */
public class Glutton extends AbstractMovableEntity {

    int nbLifes;

    //CONSTRUCTORS

    public Glutton(int nbLifes) {
        super();
        this.nbLifes = nbLifes;
    }

    public Glutton(float speed, float direction, int nbLifes) {
        super(speed, direction);
        this.nbLifes = nbLifes;
    }

    public Glutton(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction);
        this.nbLifes = nbLifes;
    }

    @Override
    public void effect(Entity e) {

    }

    @Override
    public void move() {

    }

}
