package engine;

import engine.cake.AbstractCake;
import engine.weapons.Attackable;
import log.IGLog;

import java.awt.*;

/**
 *  The player entity.
 */
public class Glutton extends AbstractMovableEntity implements Attackable {

    /**
     *  Create the glutton with the given number of life.
     *  @param nbLifes the number of life.
     */
    public Glutton(int nbLifes) {
        super();
        setLife(nbLifes);
        setCrossable(false);
    }

    /**
     *  Create the glutton with the given default speed, direction and life.
     *  @param speed the default speed
     *  @param direction the default direction
     *  @param nbLifes the default life
     */
    public Glutton(float speed, float direction, int nbLifes) {
        super(speed, direction);
        setLife(nbLifes);
        setCrossable(false);
    }

    /**
     *  Create the glutton with the given position, size, speed, direction and life.
     *  @param startPosition the default speed
     *  @param size the default size
     *  @param speed the default speed
     *  @param direction the default direction
     *  @param nbLifes the default life
     */
    public Glutton(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction);
        setLife(nbLifes);
        setCrossable(false);
    }

    @Override
    public void effect(Entity e) {
        if (e instanceof AbstractCake) {
            IGLog.info("Glutton, effect: cake.");
            getManager().removeEntity(e);
        }
    }

    @Override
    public boolean takeDamage(int damage) {
        setLife(getLife() - damage);

        IGLog.info(this + " a pris " + damage + " dommages. " + getLife() + " pv restants.");
        return getLife() <= 0;
    }
}
