package engine;

/**
 * Created by tratost on 13/04/15.
 */
public class Nutritionist extends Entity implements Movable {

    int nbLifes;

    //CONSTRUCTORS

    public Nutritionist(int nbLifes) {
        this.nbLifes = nbLifes;
    }

    @Override
    public void effect() {

    }

    @Override
    public void move() {

    }

    @Override
    public int numberOfLife() {
        return 0;
    }
}
