package engine;

import javax.swing.text.Position;
import java.awt.*;
import java.util.Observable;

public abstract class Entity extends Observable {

    private Point position;
    private Dimension size;

    /* Constructors */

    public Entity() {}

    /**
     * Construct an Entity with its start position (in x,y coordinates) and
     * its box size (as a rectangular shape).
     * @param startPosition
     * @param size
     */
    public Entity(Point startPosition, Dimension size) {
        this.position = startPosition;
        this.size     = size;
    }

    private void allNotify() {
        setChanged();
        notifyObservers();
    }

    /**
     *  Give the bounds of an Entity.
     *  @return the rectangle of the entity.
     */
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y,
                (int)size.getWidth(), (int)size.getHeight());
    }

    public Point getPoint() {
        return position;
    }

    public int getX() {
        return (int)position.getX();
    }

    public int getY() {
        return (int)position.getY();
    }

    protected void setPoint(Point p) {
        position = p;
        allNotify();
    }

    protected void setX(int x) {
        position = new Point(x, (int) position.getY());
        allNotify();
    }

    protected void setY(int y) {
        position = new Point((int) position.getX(), y);
        allNotify();
    }

    /* Entity Abstract Functionalities */

    /**
     * Apply the effect of the Entity.
     */
    public abstract void effect(Entity e);

}
