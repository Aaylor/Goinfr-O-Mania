package engine;

import log.IGLog;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *  An entity which allow to have movements.
 *  It has a speed and a direction.
 */
public abstract class AbstractMovableEntity extends Entity implements Movable {

    /**
     *  Entity's speed.
     */
    private float speed;

    /**
     *  Entity's direction.
     *  It's in degree. It will always be between 0 and 360.
     */
    private float direction;

    /**
     *  An empty entity, with null speed and null direction.
     */
    public AbstractMovableEntity() {
        super();
        speed = 0;
        direction = 0;
    }

    /**
     *  Construct an entity with the given speed and direction.
     *  @param speed entity's speed
     *  @param direction entity's direction
     */
    public AbstractMovableEntity(float speed, float direction) {
        super();
        this.speed = speed;
        this.direction = direction;
    }

    /**
     *  Create an entity with a start point, a size, a speed and a direction.
     *  @param startPosition entity's start position
     *  @param size entity's size
     *  @param speed entity's speed
     *  @param direction entity's direction
     */
    public AbstractMovableEntity(Point startPosition, Dimension size, float speed, float direction) {
        super(startPosition, size);
        this.speed = speed;
        this.direction = direction;
    }

    /**
     *  Return entity's speed.
     *  @return entity's speed.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     *  Set the new entity's speed.
     *  Negative speed is allowed.
     *  @param speed the new speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     *  Return entity's direction in Radian.
     *  @return entity's direction
     */
    public float getDirection() {
        return direction;
    }

    /**
     *  Return entity's direction in Radian.
     *  @return entity's direction in Radian.
     */
    public double getDirectionRadian() {
        return Math.toRadians(direction);
    }

    /**
     *  Set the new entity's direction.
     *  If the direction is not between 0 and 360, it will be recalculate.
     *  Negative degree will be transformed into positive.
     *  @param direction
     */
    public void setDirection(float direction) {
        this.direction = direction;
        while (this.direction < 0) this.direction += 360;
        while (this.direction >= 360) this.direction -= 360;
    }

    /**
     *  Add the new degree for the entity.
     *  Does the same thing as : e.setDirection(e.getDirection() + direction);
     *  @param direction
     */
    public void addDirection(float direction) {
        setDirection(getDirection() + direction);
    }

    @Override
    public void move(Direction d) {
        double nextX, nextY;

        Point2D position = getPoint();
        double angle = getDirectionRadian();
        Dimension entitySize = getSize();

        Point2D newPoint = null;
        Rectangle2D newRectangle = null;
        switch (d) {
            case FRONT:
                nextX = position.getX() + (getSpeed() * Math.cos(angle));
                nextY = position.getY() + (getSpeed() * Math.sin(angle));
                newPoint = new Point2D.Double(nextX, nextY);

                newRectangle = new Rectangle2D.Double(nextX, nextY,
                        entitySize.getWidth(), entitySize.getHeight());

                if (!getManager().hasCrossCollision(this, newRectangle))
                    setPoint(newPoint);

                break;

            case BELOW:
                nextX = position.getX() - (getSpeed() * Math.cos(angle));
                nextY = position.getY() - (getSpeed() * Math.sin(angle));
                newPoint = new Point2D.Double(nextX, nextY);

                newRectangle = new Rectangle2D.Double(nextX, nextY,
                        entitySize.getWidth(), entitySize.getHeight());

                if (!getManager().hasCrossCollision(this, newRectangle))
                    setPoint(newPoint);

                break;

            case LEFT:
                System.out.println("direction: " + getDirection());
                System.out.println("new direction: " + (getDirection() - (1f * getSpeed())));
                addDirection(-1f);
                break;

            case RIGHT:
                System.out.println("direction: " + getDirection());
                System.out.println("new direction: " + (getDirection() + (1f * getSpeed())));
                addDirection(1f);
                break;

            default:
                IGLog.error("Glutton::move -> Unknown direction.");
                break;
        }
    }

    @Override
    public void translate(double dx, double dy) {
        setPoint(new Point2D.Double(getX() + dx, getY() + dy));
    }

    @Override
    public void moveTo(double x, double y) {
        setPoint(new Point2D.Double(x, y));
    }

    @Override
    public abstract void effect(Entity e);

}
