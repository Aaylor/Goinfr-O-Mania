package engine;

/**
 *  Interface to allow movements.
 */
public interface Movable {

    public enum Direction {
        FRONT, BELOW, LEFT, RIGHT
    }

    /**
     *  Movement effects.
     */
    public void move(Direction d, boolean multiple);

    public void translate(double dx, double dy);

    public void moveTo(double x, double y);

}
