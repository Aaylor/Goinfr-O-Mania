package engine;

/**
 * Created by tratost on 13/04/15.
 */
public abstract class Movable extends Entity {

    private int nbLifes;

    //CONSTRUCTORS


    public Movable(int effect, int nbLifes) {
        super();
        this.nbLifes = nbLifes;
    }

    public Movable(int nbLifes) {
        this.nbLifes = nbLifes;
    }

    public Movable() {
        this.nbLifes = 3;
    }

    //SETTERS

    public void setNbLifes(int nbLifes) {
        this.nbLifes = nbLifes;
    }

    //GETTERS

    public int getNbLifes() {
        return nbLifes;
    }
}
