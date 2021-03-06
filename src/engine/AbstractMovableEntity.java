package engine;

import engine.effects.AbstractBuff;
import graphics.Circle;
import log.IGLog;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  An entity which allow to have movements.
 *  It has a speed and a direction.
 */
public abstract class AbstractMovableEntity extends Entity implements Movable {

    private final static double BACKWARD_MODIFIER = 0.5;


    /**
     *  Entity's speed.
     */
    private float speed;

    private AtomicInteger speedModifier;

    private AtomicBoolean invulnerable = new AtomicBoolean(false);

    private Set<Class> buffs = Collections.newSetFromMap(new ConcurrentHashMap<>());



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
        speedModifier = new AtomicInteger(100);
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
        speedModifier = new AtomicInteger(100);
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
        speedModifier = new AtomicInteger(100);
        this.direction = direction;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AbstractMovableEntity e = (AbstractMovableEntity) super.clone();

        e.speed = getSpeed();
        e.direction = getDirection();

        return e;
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

    public void addSpeedModifier(int modifier) {
        System.out.println(
                "AbstractMovableEntity -> Entity {" + this + "}," +
                        " addSpeedModifier(" + modifier + "), new value = " +
                        speedModifier.addAndGet(modifier)
        );
    }

    public int getSpeedModifier() {
        return speedModifier.get();
    }

    public boolean getInvulnerable() {
        return invulnerable.get();
    }

    public void setInvulnerable(boolean invulnerable) {
        System.out.println(
                "AbstractMovableEntity::setInvulnerable -> Entity {" + this + "}, " +
                        "setInvulnerable(" + invulnerable + ")"
        );
        this.invulnerable.set(invulnerable);
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

    public void addBuff(Class c) {
        if (AbstractBuff.class.isAssignableFrom(c)) {
            IGLog.info("AbstractMovableEntity::addBuf() -> add " + c.getName());
            buffs.add(c);
        } else {
            IGLog.error("AbstractMovableEntity::addBuff() -> failure...");
        }
    }

    public boolean removeBuff(Class c) {
        IGLog.info("AbstractMovableEntity::removeBuff() -> remove " + c.getName());
        return buffs.remove(c);
    }

    public boolean hasBuff(Class c) {
        IGLog.info("AbstractMovableEntity::hasBuf() -> has " + c.getName() + "?");
        return buffs.contains(c);
    }

    @Override
    public void move(Direction d, boolean multiple) {
        double nextX, nextY;

        Point2D position = getPoint();
        double angle = getDirectionRadian();
        Dimension entitySize = getSize();

        double speed = getSpeed() * (((double)getSpeedModifier()) / 100.);
        float angleModifier = !multiple ? 2.f : 0.8f;

        Point2D newPoint;
        Circle newCircle;
        switch (d) {
            case FRONT:
                nextX = position.getX() + (speed * Math.cos(angle));
                nextY = position.getY() + (speed * Math.sin(angle));
                newPoint = new Point2D.Double(nextX, nextY);

                newCircle = new Circle(nextX, nextY, entitySize.getWidth() / 2);

                if (!getManager().outOfBound(newCircle) &&
                        !getManager().hasCrossCollision(this, newCircle))
                    setPoint(newPoint);

                break;

            case BELOW:
                nextX = position.getX() - ((speed * BACKWARD_MODIFIER) * Math.cos(angle));
                nextY = position.getY() - ((speed * BACKWARD_MODIFIER) * Math.sin(angle));
                newPoint = new Point2D.Double(nextX, nextY);

                newCircle = new Circle(nextX, nextY, entitySize.getWidth() / 2);

                if (!getManager().outOfBound(newCircle) &&
                        !getManager().hasCrossCollision(this, newCircle)) {
                    setPoint(newPoint);
                }

                break;

            case LEFT:
                addDirection(-angleModifier * getSpeed());
                break;

            case RIGHT:
                addDirection(angleModifier * getSpeed());
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

}
