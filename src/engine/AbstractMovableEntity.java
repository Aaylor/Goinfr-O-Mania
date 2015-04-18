package engine;

import java.awt.*;

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
     *  Return entity's direction.
     *  @return entity's direction
     */
    public float getDirection() {
        return direction;
    }

    /**
     *  Set the new entity's direction.
     *  If the direction is not between 0 and 360, it will be recalculate.
     *  Negative degree will be transformed into positive.
     *  @param direction
     */
    public void setDirection(float direction) {
        direction %= 360;
        this.direction = (direction < 0 ? 360 - direction : direction);
    }

    /**
     *  Add the new degree for the entity.
     *  Does the same thing as : e.setDirection(e.getDirection() + direction);
     *  @param direction
     */
    public void addDirection(float direction) {
        this.direction += direction;
        this.direction =
                (this.direction < 0 ? 360 - this.direction : this.direction);
    }

    @Override
    public abstract void move();

    @Override
    public abstract void effect();

}
