package engine;

import java.util.Observable;

/**
 * Created by tratost on 13/04/15.
 */
public abstract class Entity extends Observable {

    /* Constructors */

    public Entity() {}


    /* Entity Abstract Functionalities */

    /**
     * Apply the effect of the Entity.
     */
    public abstract void effect();


}
