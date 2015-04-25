package engine;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public abstract class Entity extends Observable {

    private Point2D position;
    private Dimension size;

    /* Constructors */

    public Entity() {}

    /**
     * Construct an Entity with its start position (in x,y coordinates) and
     * its box size (as a rectangular shape).
     * @param startPosition
     * @param size
     */
    public Entity(Point2D startPosition, Dimension size) {
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
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(position.getX(), position.getY(),
                size.getWidth(), size.getHeight());
    }

    public Point2D getPoint() {
        return position;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    protected void setPoint(Point2D p) {
        position = p;
        allNotify();
    }

    protected void setX(double x) {
        position = new Point2D.Double(x, position.getY());
        allNotify();
    }

    protected void setY(double y) {
        position = new Point2D.Double(position.getX(), y);
        allNotify();
    }

    /* Entity Abstract Functionalities */

    /**
     * Apply the effect of the Entity.
     */
    public abstract void effect(Entity e);

}
