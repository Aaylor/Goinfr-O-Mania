package engine;

/**
 *  Interface to allow movements.
 */
public interface Movable {

    public enum Direction {
        FRONT, BELOW, LEFT, RIGHT
    };

    /**
     *  Movement effects.
     */
    public void move(Direction d);

    public void translate(int dx, int dy);

    public void moveTo(int x, int y);

}
