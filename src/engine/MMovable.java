package engine;

/**
 * Created by tratost on 13/04/15.
 */
public class MMovable extends MEntity {

    private int nbLifes;

    //CONSTRUCTORS


    public MMovable(int effect, int nbLifes) {
        super(effect);
        this.nbLifes = nbLifes;
    }

    public MMovable(int nbLifes) {
        this.nbLifes = nbLifes;
    }

    public MMovable() {
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
