package engine;

import engine.weapons.Attackable;
import log.IGLog;

import java.awt.*;

/**
 *  The nutritionist entity.
 */
public class Nutritionist extends AbstractMovableEntity implements Attackable {

    /**
     *  The number of life.
     */
    int nbLifes;

    public Nutritionist(int nbLifes) {
        super();
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    public Nutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction);
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    public Nutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction);
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    @Override
    public void effect(Entity e) {

    }

    @Override
    public void takeDamage(int damage) {
        nbLifes -= damage;

        IGLog.info(this + " a pris " + damage + " dommages. " + nbLifes + " pv restants.");

        if (nbLifes <= 0) {
            getManager().removeNutritionist(this);
        }
    }
}
