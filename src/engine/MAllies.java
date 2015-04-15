package engine;

/**
 * Created by tratost on 13/04/15.
 */
public class MAllies extends MMovable {

    //CONSTRUCTORS

    public MAllies(int effect, int nbLifes) {
        super(effect, nbLifes);
    }

    public MAllies(int nbLifes) {
        super(nbLifes);
    }

    @Override
    public void setNbLifes(int nbLifes) {
        super.setNbLifes(nbLifes);
        setChanged();
        notifyObservers();
    }
}
