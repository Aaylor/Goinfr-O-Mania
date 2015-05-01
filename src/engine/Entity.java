package engine;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public abstract class Entity extends Observable {

    private Point2D position;
    private Dimension size;
    private boolean crossable;

    private EntityManager manager = null;

    /* Constructors */

    public Entity() {}

    /**
     * Construct an Entity with its start position (in x,y coordinates) and
     * its box size (as a rectangular shape).
     * @param startPosition the start position
     * @param size the size
     */
    public Entity(Point2D startPosition, Dimension size) {
        this.position = startPosition;
        this.size     = size;
        crossable     = true;
    }

    /**
     * Construct an Entity with its start position (in x,y coordinates),
     * its box size (as a rectangular shape) and the crossable state.
     * @param position the start position
     * @param size the size
     * @param crossable the crossable state
     */
    public Entity(Point2D position, Dimension size, boolean crossable) {
        this.position = position;
        this.size = size;
        this.crossable = crossable;
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

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public boolean isCrossable() {
        return crossable;
    }

    public void setCrossable(boolean crossable) {
        this.crossable = crossable;
    }

    /* Entity Abstract Functionalities */

    /**
     * Apply the effect of the Entity.
     */
    public abstract void effect(Entity e);

}
