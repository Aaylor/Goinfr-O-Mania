package engine;

import engine.weapons.Weapon;
import graphics.Circle;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public abstract class Entity extends Observable implements Cloneable {

    private Point2D position;
    private Dimension size;
    private boolean crossable;
    private int maxLife;
    private int life;
    private Weapon weapon;

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
        maxLife = 0;
        life = 0;
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
        maxLife = 0;
        life = 0;
    }

    private void allNotify() {
        setChanged();
        notifyObservers();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Entity e = (Entity) super.clone();

        e.position  = null;
        e.size      = (Dimension) e.getSize().clone();
        e.maxLife   = getMaxLife();
        e.life      = getMaxLife();
        e.crossable = isCrossable();

        if (getWeapon() != null) {
            e.weapon = Weapon.make(getWeapon().getName());
        } else {
            e.weapon = null;
        }

        return e;
    }

    /**
     *  Give the bounds of an Entity.
     *  @return the rectangle of the entity.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(position.getX(), position.getY(),
                size.getWidth(), size.getHeight());
    }

    public Circle getBoundsCircle() {
        return new Circle(getX(), getY(), size.getWidth() / 2);
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

    public Point2D getCenter() {
        return new Point2D.Double(getCenterX(), getCenterY());
    }

    public double getCenterX() {
        return getX() + getSize().getWidth() / 2;
    }

    public double getCenterY() {
        return getY() + getSize().getHeight() / 2;
    }

    public void setPoint(Point2D p) {
        position = p;
        allNotify();
    }

    public void setX(double x) {
        position = new Point2D.Double(x, position.getY());
        allNotify();
    }

    public void setY(double y) {
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        if (this.life == 0)
            this.maxLife = life;

        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        weapon.setOwner(this);
    }


    /* Entity Abstract Functionalities */

    /**
     * Apply the effect of the Entity.
     */
    public abstract boolean effect(Entity e);

}
