package engine;

import java.awt.*;

public class Nutritionist extends AbstractMovableEntity {

    int nbLifes;

    //CONSTRUCTORS

    public Nutritionist(int nbLifes) {
        super();
        this.nbLifes = nbLifes;
    }

    public Nutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction);
        this.nbLifes = nbLifes;
    }

    public Nutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction);
        this.nbLifes = nbLifes;
    }

    @Override
    public void move(Direction d) {

    }

    @Override
    public void effect(Entity e) {

    }

}
