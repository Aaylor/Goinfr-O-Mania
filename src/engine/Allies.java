package engine;

/**
 * Created by tratost on 13/04/15.
 */
public class Allies extends Movable {

    //CONSTRUCTORS

    public Allies(int effect, int nbLifes) {
        super(effect, nbLifes);
    }

    public Allies(int nbLifes) {
        super(nbLifes);
    }

    @Override
    public void setNbLifes(int nbLifes) {
        super.setNbLifes(nbLifes);
        setChanged();
        notifyObservers();
    }
}
