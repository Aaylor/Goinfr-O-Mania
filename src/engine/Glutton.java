package engine;

import engine.weapons.Attackable;
import log.IGLog;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *  The player entity.
 */
public class Glutton extends AbstractMovableEntity implements Attackable {

    /**
     *  The number of life of the glutton.
     */
    private int nbLifes;

    /**
     *  Create the glutton with the given number of life.
     *  @param nbLifes the number of life.
     */
    public Glutton(int nbLifes) {
        super();
        this.nbLifes = nbLifes;
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
        this.nbLifes = nbLifes;
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
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    @Override
    public void effect(Entity e) {
        if (e instanceof Cake) {
            IGLog.info("Glutton, effect: cake.");
            getManager().removeEntity(e);
        }
    }

    @Override
    public void takeDamage(int damage) {
        nbLifes -= damage;

        IGLog.info(this + " a pris " + damage + " dommages. " + nbLifes + " pv restants.");

        if (nbLifes <= 0) {
            IGLog.info("I'm invicible mwahahahahaha !");

            /* TODO: killed character */
        }
    }
}
