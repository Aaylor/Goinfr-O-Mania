package engine;

/**
 *  Interface to allow movements.
 */
public interface Movable {

    /**
     *  Movement effects.
     */
    public void move();

    public void translate(int dx, int dy);

    public void moveTo(int x, int y);

}
