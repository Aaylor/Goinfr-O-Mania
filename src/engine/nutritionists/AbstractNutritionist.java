package engine.nutritionists;

import engine.AbstractMovableEntity;
import engine.Entity;
import engine.weapons.Attackable;
import log.IGLog;

import java.awt.*;

/**
 *  The nutritionist entity.
 */
public abstract class AbstractNutritionist extends AbstractMovableEntity
        implements Attackable, ArtificialIntelligence {

    /**
     *  The number of life.
     */
    int nbLifes;

    public AbstractNutritionist(int nbLifes) {
        super();
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    public AbstractNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction);
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    public AbstractNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
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
